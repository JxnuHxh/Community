package com.wowo.time.colltest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorld {
    @GetMapping("/")
    public String index() {

        return "index";
    }

}
