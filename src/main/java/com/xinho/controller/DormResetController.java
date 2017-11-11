package com.xinho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dormReset")
public class DormResetController {

    @RequestMapping
    public String init(){
        return "dormReset";
    }

}
