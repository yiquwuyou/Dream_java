package com.example.springboot2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertiesController {

    // 读取配置文件
    @Value("${demo.key1}")
    private String key1;

    @RequestMapping("readKey")
    public String readKey(){
        return "读取到的配置项key1：" + key1;
    }
}
