package com.xinho.controller;

import com.xinho.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dormReset")
public class DormResetController {

    @Autowired private AcademyService academyService;
    @Autowired private ApartmentService apartmentService;
    @Autowired private DicService dicService;
    @Autowired private DormitoryService dormitoryService;
    @Autowired private DormDistriService dormDistriService;

    @RequestMapping
    public String init(Model model){
        model.addAttribute("academyDtos",academyService.getAllAcademy());
        model.addAttribute("apartDtos",apartmentService.searchAllApartment());
        model.addAttribute("dicGradeList",dicService.getDicByPId(10001));
        return "dormReset";
    }


}
