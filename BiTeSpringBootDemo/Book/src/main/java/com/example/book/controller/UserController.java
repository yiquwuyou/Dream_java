package com.example.book.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserController {

    @RequestMapping("/login")
    public Boolean login(String userName, String password, HttpSession session){
        // 校验参数
        if(!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)){
            return false;
        }

        // 验证账号密码是否正确
        /**
         * if(userName.equals("admin")){ }   这种写法，如果userName为null，会报空指针异常
         * 所以把常量放前面
         * 开发习惯，直接养成
         */
        if("admin".equals(userName) && "admin".equals(password)){
            // 账号密码正确
            // 存Session
            session.setAttribute("userName",userName);
            return true;
        }
        return false;
    }
}
