package com.xinho.controller;

import com.xinho.bean.Class;
import com.xinho.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @ResponseBody
    @RequestMapping(value = "/pid",method = RequestMethod.GET)
    public List<Class> getClassesByMajorId(@RequestParam(value = "majorId") Integer majorId,
                                           @RequestParam(value = "dicGrade") Integer dicGrade){
        List<Class> classList = classService.getClassesByMajorIdAndGradeId(majorId,dicGrade);
        return classList;
    }

}
