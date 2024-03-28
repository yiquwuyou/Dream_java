package com.example.questionnaire.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserLog {
    private int id;
    private String username;
    private int role;                // 1-添加 2-修改 3-删除
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
