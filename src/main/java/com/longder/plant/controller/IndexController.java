package com.longder.plant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    /**
     * 主页默认跳转到登陆页
     * @return
     */
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
