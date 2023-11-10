package com.example.springboot2.service;

import com.example.springboot2.config.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public static UserInfo userInfo = new UserInfo();
    public void doService(){
        System.out.println("do service...");
    }
}
