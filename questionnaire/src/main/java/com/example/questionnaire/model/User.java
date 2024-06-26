package com.example.questionnaire.model;

import lombok.Data;

import java.time.LocalDateTime;

// 下面都是些默认的值
@Data
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
