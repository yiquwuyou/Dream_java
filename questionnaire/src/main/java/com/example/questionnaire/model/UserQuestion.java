package com.example.questionnaire.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserQuestion {
    private Integer id;
    private String uuid;
    private String username;
    private String title;
    private String content;
    private Integer isPublish;
    private Integer answerNumber;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}