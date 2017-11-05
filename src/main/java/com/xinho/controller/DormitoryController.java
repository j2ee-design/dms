package com.xinho.controller;

import com.xinho.bean.Dormitory;
import com.xinho.dto.DormitoryDto;
import com.xinho.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dorm")
public class DormitoryController {

    @Autowired
    private DormitoryService dormitoryService;

    @RequestMapping(value = "/pid",method = RequestMethod.GET)
    @ResponseBody
    public List<DormitoryDto> getDormListByClassId(@RequestParam(value = "classId")Integer classId){
        List<DormitoryDto> dormitoryDtoList = dormitoryService.getDormListByClassId(classId);
        return dormitoryDtoList;
    }


}
