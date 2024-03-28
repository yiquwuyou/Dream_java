package com.example.questionnaire.controller;

import com.example.questionnaire.model.Result;
import com.example.questionnaire.model.UserJwt;
import com.example.questionnaire.model.UserLog;
import com.example.questionnaire.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userlog")
@CrossOrigin(origins="*")
public class UserLogController {

    @Autowired
    private UserLogService userLogService;


    // 添加日志
    @RequestMapping("/add")
    public Result insertUserLog(@RequestBody Map<String, String> mapParams) {
        String username = UserJwt.getUsername();
        int role = Integer.parseInt(mapParams.get("role"));
        String content = mapParams.get("content");
        userLogService.insertUserLog(username, role, content);
        return Result.success("添加日志成功");
    }

    // 查看添加日志
    @RequestMapping("/selectAdd")
    public Result selectAddLog() {
        return Result.success(userLogService.selectAddLog());
    }

    // 查看修改日志
    @RequestMapping("/selectUpdate")
    public Result selectUpdateLog() {
        return Result.success(userLogService.selectUpdateLog());
    }

    // 查看删除日志
    @RequestMapping("/selectDelete")
    public Result selectDeleteLog() {
        return Result.success(userLogService.selectDeleteLog());
    }

    // 查看我修改过谁的
    @RequestMapping("/selectUpdateMe")
    public Result selectUpdateMe() {
        return Result.success(userLogService.selectUpdateMe());
    }

    // 查看谁修改过我的
    @RequestMapping("/selectUpdateByMe")
    public Result selectUpdateByMe() {
        return Result.success(userLogService.selectUpdateByMe());
    }

    // 查看所有日志
    @RequestMapping("/selectAll")
    public Result selectAllLog() {
        return Result.success(userLogService.selectAllLog());
    }
}