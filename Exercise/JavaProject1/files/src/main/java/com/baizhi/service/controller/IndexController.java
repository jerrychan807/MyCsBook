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

    @GetMapping("pass02")
    public String toPass02() {
        return "pass02";
    }

    @GetMapping("pass02result")
    public String toPass02res() {
        return "pass02result";
    }

    @GetMapping("pass03")
    public String toPass03() {
        return "pass03";
    }

    @GetMapping("pass04")
    public String toPass04() {
        return "pass04";
    }

    @GetMapping("pass05")
    public String toPass05() {
        return "pass05";
    }

    @GetMapping("pass06")
    public String toPass06() {
        return "pass06";
    }

    @GetMapping("pass07")
    public String toPass07() {
        return "pass07";
    }

    @GetMapping("pass08")
    public String toPass08() {
        return "pass08";
    }

    @GetMapping("pass09")
    public String toPass09() {
        return "pass09";
    }

    @GetMapping("pass10")
    public String toPass10() {
        return "pass10";
    }
}
