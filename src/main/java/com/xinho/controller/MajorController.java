package com.xinho.controller;

import com.xinho.dto.MajorDto;
import com.xinho.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/major")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @ResponseBody
    @RequestMapping(value = "/pid",method = RequestMethod.GET)
    public List<MajorDto> getMajorByPid(@RequestParam(value = "academyId") Integer academyId){
        List<MajorDto> majorDtoList = majorService.getMajorsByPid(academyId);
        return majorDtoList;
    }
}
