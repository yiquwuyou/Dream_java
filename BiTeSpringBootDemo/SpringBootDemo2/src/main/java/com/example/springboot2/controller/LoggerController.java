package com.example.springboot2.controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j     // 使用该注解后Spring会自动帮我们生成日志对象 —— log
@RestController
public class LoggerController {

//    private static Logger logger = LoggerFactory.getLogger(LoggerController.class);
    // 日志对象的名称， 通常就写成上面那个用法，LoggerController.class
//    private static Logger logger = LoggerFactory.getLogger("aaa");


    @PostConstruct
    public void print(){
        System.out.println("打印日志");
        log.info("=================我是日志框架打印的日志================");
        log.info("=======我是日志框架打印的日志===================");
        log.error("我是error日志");
        log.warn("我是warn日志");
        log.info("我是info日志");
        log.debug("我是debug日志");
        log.trace("我是trace日志");
    }
}
