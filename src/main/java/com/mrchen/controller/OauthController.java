package com.mrchen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/10/9 17:36
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {
    @RequestMapping("/aaa")

    public String test(){
        return "aaaa";
    }
}
