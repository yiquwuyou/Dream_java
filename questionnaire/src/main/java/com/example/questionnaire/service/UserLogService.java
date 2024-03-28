package com.example.questionnaire.service;

import com.example.questionnaire.mapper.UserLogMapper;
import com.example.questionnaire.model.UserJwt;
import com.example.questionnaire.model.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogService {

    @Autowired
    private UserLogMapper userLogMapper;


    // 添加日志
    public void insertUserLog(String username, int role, String content) {
        userLogMapper.insertUserLog(username, role, content);
    }

    // 查看添加日志
    public List<UserLog> selectAddLog() {
        return userLogMapper.selectAddLog(UserJwt.getUsername());
    }

    // 查看修改日志
    public List<UserLog> selectUpdateLog() {
        return userLogMapper.selectUpdateLog(UserJwt.getUsername());
    }

    // 查看删除日志
    public List<UserLog> selectDeleteLog() {
        return userLogMapper.selectDeleteLog(UserJwt.getUsername());
    }

    // 查看我修改过谁的
    public List<UserLog> selectUpdateMe() {
        return userLogMapper.selectUpdateMe(UserJwt.getUsername());
    }

    // 查看谁修改过我的
    public List<UserLog> selectUpdateByMe() {
        return userLogMapper.selectUpdateByMe(UserJwt.getUsername());
    }

    // 查看所有日志
    public List<UserLog> selectAllLog() {
        return userLogMapper.selectAllLog(UserJwt.getUsername());
    }
}