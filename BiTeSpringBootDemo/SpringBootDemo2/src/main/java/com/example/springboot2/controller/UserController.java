package com.example.springboot2.controller;

import com.example.springboot2.config.UserInfo;
import com.example.springboot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class UserController {

    // 属性注入
    @Autowired
    private UserService userService;

//    @Qualifier("userInfo2")   // 指定属性名
//    @Autowired
    @Resource(name = "userInfo2")   // 通过@Resource指定bean
    private UserInfo userInfo;

    // 构造方法注入
//    private UserService userService;
//    private UserInfo userInfo;
//    // 当有多个购房函数时
//    // 有无参的构造方法时，默认用无参的构造方法
//    // 当没有无参的构造函数时，程序依然报错
//    // 需要用@Autowired告诉Spring需要使用哪个构造函数
////    public UserController() {
////    }
//
//    public UserController(UserService userService, UserInfo userInfo) {
//        this.userService = userService;
//        this.userInfo = userInfo;
//    }
//
////    @Autowired
//    public UserController(UserService userService){
//        this.userService = userService;
//    }

    // Setter方法注入
//    private UserService userService;
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    public void doUserController(){
        userService.doService();
        System.out.println(userInfo);
        System.out.println("do Controller...");
    }
}

