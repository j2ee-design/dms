package com.xinho.controller;

import com.xinho.bean.Student;
import com.xinho.constant.PageCodeEnum;
import com.xinho.dto.ApartmentDto;
import com.xinho.dto.DormitoryDto;
import com.xinho.dto.StudentDto;
import com.xinho.service.ApartmentService;
import com.xinho.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dormitory")
public class DormitoryController {

    @Autowired private DormitoryService dormitoryService;
    @Autowired private ApartmentService apartmentService;

    /**
     * 根据 apartment id查找对应
     * @param apartId 要查找宿舍的楼 ID
     * @return 宿舍jsp
     */
    @RequestMapping("/{apartId}")
    public String getDormList(@PathVariable("apartId") Integer apartId, Model model){
        List<DormitoryDto> dormitoryDtoList = dormitoryService.getDormListByApartId(apartId);
        ApartmentDto apartmentDto = apartmentService.searchByApartId(apartId);
        model.addAttribute("dormDtos",dormitoryDtoList);
        model.addAttribute("apartDto",apartmentDto);
        return "dormList";
    }

    /**
     * 通过班级ID查找宿舍（StudentList）
     * @param classId
     * @return
     */
    @RequestMapping(value = "/pid",method = RequestMethod.GET)
    public @ResponseBody List<DormitoryDto> getDormListByClassId(@RequestParam(value = "classId")Integer classId){
        List<DormitoryDto> dormitoryDtoList = dormitoryService.getDormListByClassId(classId);
        return dormitoryDtoList;
    }

    public Map distribute(){
        Map result = new HashMap();


        return result;
    }

    /**
     * 用户点击宿舍列表页中具体的某个宿舍项，获取该宿舍信息。
     * 1. 宿舍信息
     * 2. 学生信息
     * @return
     */
    @RequestMapping("/get/{id}")
    public @ResponseBody Map getDormInfo(@PathVariable("id") Integer dormId){
        Map result = new HashMap();
        String gradeName = null;
        List<StudentDto> studentDtoList = null;

        // 获取宿舍信息
        DormitoryDto dormitoryDto = dormitoryService.getDormListById(dormId);
        // 如果该宿舍入住学生不为0，则获取学生信息
        if (dormitoryDto.getUsedBed()!=null && dormitoryDto.getUsedBed().longValue() != 0){
            studentDtoList = dormitoryService.getStudentDtoListByDormId(dormId);
        }

        result.put("dormitoryDto",dormitoryDto);
        result.put("studentDtos",studentDtoList);
        return result;
    }

    /**
     * 添加单个学生至宿舍，apartList 页面点击添加学生至宿舍。
     * @param dormId 宿舍ID
     * @param stuId 学生 ID
     * @return
     */
    @RequestMapping("/distriSingle")
    public @ResponseBody Map distriSingle(@RequestParam(value="dormId") Integer dormId, @RequestParam(value="stuId") Integer stuId){
        Map result = new HashMap();
        int pageCode = dormitoryService.distributeSingle(dormId,stuId);
        result.put(PageCodeEnum.KEY, pageCode);
        return result;
    }

}
