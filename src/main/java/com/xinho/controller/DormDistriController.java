package com.xinho.controller;

import com.xinho.dto.DormitoryDto;
import com.xinho.service.AcademyService;
import com.xinho.service.ApartmentService;
import com.xinho.service.DicService;
import com.xinho.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/distribute")
public class DormDistriController {

    @Autowired private AcademyService academyService;
    @Autowired private ApartmentService apartmentService;
    @Autowired private DicService dicService;
    @Autowired private DormitoryService dormitoryService;
    /**
     * 初始化分配页面
     * 1. 返回学院、年级和公寓楼信息
     * 2. 返回逻辑视图
     * @return 逻辑视图 dormDistri
     */
    @RequestMapping
    public String init(Model model){
        // 1.
        model.addAttribute("academyDtos",academyService.getAllAcademy());
        model.addAttribute("apartDtos",apartmentService.searchAllApartment());
        model.addAttribute("dicGradeList",dicService.getDicByPId(10001));
        return "dormDistri";
    }

    @RequestMapping("/getDorm/{apartId}")
    public @ResponseBody Map getDorm(@PathVariable("apartId") Integer apartId){
        Map result = new HashMap();
        List<DormitoryDto> dormitoryDtos = dormitoryService.getDormListByApartId(apartId);
        result.put("dormDtos",dormitoryDtos);
        result.put("apartDto",apartmentService.searchByApartId(apartId));
        return result;
    }

}
