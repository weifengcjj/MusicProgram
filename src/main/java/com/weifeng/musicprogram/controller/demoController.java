package com.weifeng.musicprogram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {
    @GetMapping("/hh")
    public String hh(){
        return "你好";
    }
}
