package com.example.demo.service;


import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Service 里面放函数的函数体（函数内部的代码，其实主要是给函数写个返回值）
// 方法的具体实现在 xml 里面写着，是 SQL 语句

// @Service 用于标识一个类是服务类
// 服务类通常负责与数据访问层（如数据库）进行交互，处理复杂的业务逻辑，以及协调其他组件或服务来完成特定的任务
// 服务类主要起中转的作用
@Service
public class UserService {

    // Service 不会直接操作数据库，所以这里将 UserMapper 注入进来
    @Autowired
    private UserMapper userMapper;

    public int reg(UserInfo userInfo) {
        return userMapper.reg(userInfo);
    }

    public UserInfo login(String username) {
        return userMapper.login(username);
    }
}
