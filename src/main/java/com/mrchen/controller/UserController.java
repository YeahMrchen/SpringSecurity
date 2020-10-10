package com.mrchen.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/25 10:26
 */
@RestController
@RequestMapping("/login")
public class UserController {

    @RequestMapping("/success")
    public String login() {
        return "登陆成功";
    }

    @RequestMapping(value = "/admin")
    public String loginAdmin() {
        return "success";
    }

    @RequestMapping("/all")
    public String loginAll() {
        return "success";
    }

    @RequestMapping("/r1")
    public String loginR1() {
        return "success";
    }

    @RequestMapping("/r2")
    public String loginR2() {
        return "success";
    }

    @RequestMapping("/getInfo")
    public Authentication getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityContext context = SecurityContextHolder.getContext();
        return authentication;
    }
}
