package com.xinho.controller;

import com.xinho.bean.Student;
import com.xinho.constant.PageCodeEnum;
import com.xinho.dto.StudentDto;
import com.xinho.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentAddController {

    @Autowired
    private StudentService studentService;

    @RequestMapping
    public String init(){
        return "redirect:/student/add";
    }

    /**
     * Restful 风格的url，
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String initAdd() {
        return "studentAdd";
    }

    /**
     * 增加动作-->添加单个学生
     * 1. 表单验证（我是使用了简单的自定义验证）TODO 改进，用 SpringMVC 的 Validate
     * 2. 数据库添加单个学生。
     * 3. 根据结果返回不同的状态码
     * @param studentDto 学生数据
     * @return Map{错误输入项的name，状态码}
     */
    @RequestMapping(value = "/addSingle", method = RequestMethod.POST)
    @ResponseBody
    public Map addSingle(@RequestBody StudentDto studentDto) {
        Map data = new HashMap();
        // 1. 表单验证,过滤掉错误输入，获取返回验证错误值的 name-List
        List<String> errorNameList = studentService.validate(studentDto);

        // 2. sql 操作以及信息码返回 -> 3.
        if (errorNameList.size() == 0 && studentService.addSingle(studentDto)) {
            // 如果输入正确，写入数据库，返回成功信息
            data.put(PageCodeEnum.KEY, PageCodeEnum.STUDENT_ADD_SUCCESS.getMsg());
        } else {
            // 如果失败，返回错误 name 列表以及错误信息
            data.put(PageCodeEnum.KEY, PageCodeEnum.STUDENT_ADD_FAIL.getMsg());
        }

        data.put("errorInputList", errorNameList);
        return data;
    }

    /**
     * 进入列表初始化页面
     * 1. 向后台发送年级和学院信息
     * 2. 按照"全部"条件查找第一页，一页30条
     * @return
     */
    @RequestMapping("/listInit")
    public String initList(){
        return "studentList";
    }

    /**
     * 根据页面条件条件查询学生
     * 1. 分页的实现两种思路：
     *    1.1 要分页的地方调用专门分页的函数（所以要专门写一个分页的 dao 方法），传入 page 信息即可。
     *    1.2 使用 mybatis 拦截器，标志要分页的请求，在 mybatis 发起请求的时候打断并修改请求 sql 语句。
     * @param studentDto
     * @return
     */
    @@RequestMapping
    @ResponseBody
    public List<Student> getStudent(StudentDto studentDto){
        List<Student> studentList = studentService.searchStudent(studentDto);

        return studentList;
    }
    /*
    * 两种方法：
    * 1. 初始化仅仅传送页面，数据由另一个来执行
    * 2. 查询由初始化来做，其他的传入查询参数（覆盖默认参数）
    */

    /**
     * 删除动作-->删除在ID列表中的学生
     * @return 返回成功删除学生的数量和状态码
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Map delete(List<Integer> studentIdList){
        Map result = new HashMap();
        int successNum = 0;
        try {
            successNum = studentService.removeStudentById(studentIdList);
        }catch (Exception e){
            // 给前端状态码而不是提示信息好很多
            result.put(PageCodeEnum.KEY,PageCodeEnum.STUDENT_REMOVE_FAIL.getCode());
            return result;
        } finally {
            result.put("successNum",successNum);
        }
        result.put(PageCodeEnum.KEY,PageCodeEnum.STUDENT_REMOVE_SUCCESS.getCode());
        return result;
    }
}
