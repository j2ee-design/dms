package com.xinho.controller;

import com.xinho.constant.PageCodeEnum;
import com.xinho.dto.StudentDto;
import com.xinho.service.AcademyService;
import com.xinho.service.DicService;
import com.xinho.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AcademyService academyService;

    @Autowired
    private DicService dicService;

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
     * 2. 数据准确信检查、逻辑检查，重复性数据检查
     * 3. 数据库添加单个学生。
     * @param studentDto 学生数据
     * @return Map{错误输入项的name，状态码}
     */
    @RequestMapping(value = "/addSingle", method = RequestMethod.POST)
    public @ResponseBody Map addSingle(@RequestBody StudentDto studentDto) {
        Map data = new HashMap();
        // 1. 表单验证,过滤掉错误输入，获取返回验证错误值的 name-List
        List<String> errorNameList = studentService.validate(studentDto);
        if (errorNameList.size()>0){
            data.put(PageCodeEnum.KEY, PageCodeEnum.STUDENT_ADD_SUCCESS.getMsg());
            return data;
        }
        // 2.1 检查所添加的学院、专业信息是否存在
        if (!studentService.validateInfoExact(studentDto)){
            data.put(PageCodeEnum.KEY,PageCodeEnum.STUDENT_ADD_FAIL_DATA_NOT_EXIT.getMsg());
            return data;
        }
        // 2.3 检查专业+年级和班级ID是否匹配（也就是该班级是否存在与该年级该专业下）
        if (!studentService.validateAddLogic(studentDto)){
            data.put(PageCodeEnum.KEY,PageCodeEnum.STUDENT_ADD_FAIL_Logic.getMsg());
            return data;
        }
        // 2.2 学生学号是否已经存在
        if (studentService.searchStudentById(studentDto.getId())!=null){
            data.put(PageCodeEnum.KEY,PageCodeEnum.STUDENT_ADD_FAIL_DATA_Student_EXIT.getMsg());
            return data;
        }

        // 3. sql 操作
        studentService.addSingle(studentDto);
        data.put(PageCodeEnum.KEY,PageCodeEnum.STUDENT_ADD_SUCCESS.getMsg());
        return data;
    }

    /**
     * 进入列表初始化页面
     * 1. 向后台发送年级和学院信息
     * 2. 按照"全部"条件查找第一页，一页30条
     * @return 逻辑视图-->studentList
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String initList(Model model){
        // 初始化搜索条件为空进行查询
        model.addAttribute("studentDtoList",studentService.searchAllStudent());
        // 加入所有学院信息
        model.addAttribute("academyList",academyService.getAllAcademy());
        // 加入所有年级信息
        model.addAttribute("dicGradeList",dicService.getDicByPId(10001));
        return "studentList";
    }

    /**
     * 根据页面条件条件查询学生.studentList.jsp 用
     * 1. 分页的实现两种思路：
     *    1.1 要分页的地方调用专门分页的函数（所以要专门写一个分页的 dao 方法），传入 page 信息即可。
     *    1.2 使用 mybatis 拦截器，标志要分页的请求，在 mybatis 发起请求的时候打断并修改请求 sql 语句。
     * @param studentDto 查询的 dto
     * @return
     */
    @RequestMapping(value = "/list/search",method = RequestMethod.POST)
    public @ResponseBody List<StudentDto> getStudent(@RequestBody StudentDto studentDto){
        List<StudentDto> studentDtoList = studentService.searchStudent(studentDto);
        return studentDtoList;
    }

    // TODO 不得不说，你这URL取的真丑。垃圾。早日学RESTful早日优雅起来。
    @RequestMapping("/searchSingle/{id}")
    public @ResponseBody StudentDto searchSingle(@PathVariable("id") Integer id){
        StudentDto studentDto = studentService.searchStudentById(id);
        return studentDto;
    }

    /**
     * 学生信息更新的基本逻辑：
     * 1. 校检用户输入
     * 2. 补全一个完整的学生信息
     * 3. 检查数据的真实性。(这一步或许应该放在第一步)
     * 4. 进行逻辑检查
     * 5. 写入(注意更新宿舍信息，这里用事务)
     * @param studentDto
     * @return
     */
    @RequestMapping("/modify")
    public @ResponseBody Map modifyStudent(@RequestBody StudentDto studentDto){
        Integer dormName = studentDto.getDormName();
        Map result = new HashMap();
        // 1. 数据格式校检[这里的数据格式校检和添加的格式校检不同！]
        if (studentService.validateModify(studentDto).size()!=0){
            result.put(PageCodeEnum.KEY,PageCodeEnum.MODIFY_FAIL_DATA_VALIDATE.getCode());
            return result;
        }
        // 2. 数据真实性校检
        if (!studentService.modifyIsDataReal(studentDto)){
            result.put(PageCodeEnum.KEY,PageCodeEnum.MODIFY_FAIL_WRITE_TO_DB.getCode());
            return result;
        }
        // 3. 数据之间逻辑校检(还要校检新的宿舍是否住满)
        if (!studentService.modifyLogicValidate(studentDto)){
            result.put(PageCodeEnum.KEY,PageCodeEnum.MODIFY_FAIL_LOGIC_VALIDATE.getCode());
            return result;
        }
        // 4.补全一个完整的用户信息，注意根据宿舍Name有无修改宿舍Status
        studentService.finishStudentInfo(studentDto);

        // 5. 写入
        if (studentService.modifyStudentByDto(studentDto)){
            result.put(PageCodeEnum.KEY,PageCodeEnum.MODIFY_SUCCESS.getCode());
            // 写入成功，更新宿舍信息
            studentService.updateDorm(dormName,studentDto.getId());
        } else {
            result.put(PageCodeEnum.KEY,PageCodeEnum.MODIFY_FAIL.getCode());
        }
        return result;
    }

    /**
     * 删除动作-->删除在ID列表中的学生
     * @return 返回成功删除学生的数量和状态码
     */
    @RequestMapping(value = "/delete/{idList}")
    public String delete(@PathVariable("idList") String idStr,RedirectAttributes model){
        int[] results;
        results = studentService.removeStudentByIdStr(idStr);
        model.addFlashAttribute("deleteNum",results[0]);
        model.addFlashAttribute("allNum",results[1]);
        return "redirect:/student/list";
    }
}
