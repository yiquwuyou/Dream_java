package com.example.mybatis.controller;


import com.example.mybatis.mapper.UserInfoMapper;
import com.example.mybatis.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserInfoController {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping("/selectAll")
    public List<UserInfo> selectAll(){
        return userInfoMapper.selectAll();
    }
}
