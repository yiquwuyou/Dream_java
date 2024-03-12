package com.example.cvresume.service;

import com.example.cvresume.mapper.UserMapper;
import com.example.cvresume.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    // 注册 (把用户插入到数据库中)
    public Integer insertUser(User user){
        return userMapper.insertuser(user);
    }

    // 根据用户名查询用户信息 -> 登录
    public User selectByName(String username){
        return userMapper.selectByName(username);
    }

    // 查询用户id
    public Integer selectIdByUserName(String username){
        return userMapper.selectIdByUserName(username);
    }

    // 更新用户信息
    public Integer updateUser(User user){
        return userMapper.updateUser(user);
    }
}
