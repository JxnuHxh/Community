package com.wowo.time.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Publishcontroller {
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

}
