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

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/list")
    public String initList(){
        return "studentList";
    }
}
