package com.xinho.controller;

import com.xinho.bean.SysUser;
import com.xinho.constant.PageCodeEnum;
import com.xinho.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired private SysUserService sysUserService;
    @Autowired private HttpSession session;

    @RequestMapping
    public String init() {
        return "login";
    }

    /**
     * 用户登录校检
     * 1. 数据校检
     * 2. 数据库查询，密码校检
     * 3. 返回不同页面
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("validate")
    public String validate(SysUser user, RedirectAttributes model){
        if (!sysUserService.validateDate(user)){
            model.addFlashAttribute("msg",PageCodeEnum.USER_LOGIN_FAIL_DATA_ILLIGLE.getMsg());
            return "redirect:/login";
        }
        if (!sysUserService.validate(user)){
            model.addFlashAttribute("msg",PageCodeEnum.USER_LOGIN_FAIL_VALIDATE.getMsg());
            return "redirect:/login";
        }
        // 登录成功，保存登录状态。TODO 这里应该用一个token


        return "main";
    }
}
















