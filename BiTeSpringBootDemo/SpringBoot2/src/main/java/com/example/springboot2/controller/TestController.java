package com.example.springboot2.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@RequestMapping("/test")
@Controller
//@Service
@ResponseBody
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "测试Controller和其他注解的区别";
    }
}
