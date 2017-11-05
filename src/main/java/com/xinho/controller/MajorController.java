package com.xinho.controller;

import com.xinho.dto.MajorDto;
import com.xinho.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/major")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @RequestMapping("/pid/{pid}")
    public List<MajorDto> getMajorByPid(@PathVariable(value = "pid") Integer academyId){
        List<MajorDto> majorDtoList = majorService.getMajorsByPid(academyId);
        return majorDtoList;
    }
}
