package com.xinho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class InitLoginController {

    @RequestMapping
    public String init() {
        return "login";
    }
}
