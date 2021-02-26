package com.baizhi.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("index")
    public String toLogin() {
        return "login";
    }

    @GetMapping("pass01")
    public String toPass01() {
        return "pass01";
    }

    @GetMapping("pass01result")
    public String toPass01res() {
        return "pass01result";
    }
}
