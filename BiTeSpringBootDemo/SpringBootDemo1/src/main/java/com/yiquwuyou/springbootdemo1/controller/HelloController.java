package com.yiquwuyou.springbootdemo1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping(value = "/sayhi",method = RequestMethod.POST)
    public String sayHi(){
        return "Hi,SpringBoot";
    }

    @RequestMapping("/sayhello")
    public String sayHello(){
        return "Hello,SpringBoot";
    }
}