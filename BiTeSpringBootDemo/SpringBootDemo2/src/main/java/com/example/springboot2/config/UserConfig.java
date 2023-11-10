package com.example.springboot2.config;

import org.springframework.context.annotation.Configuration;

// 配置层，处理项目中的一些配置信息
@Configuration
public class UserConfig {
    public void doConfig(){
        System.out.println("do configuration...");
    }
}
