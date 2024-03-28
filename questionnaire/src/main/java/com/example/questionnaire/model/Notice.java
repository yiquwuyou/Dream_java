package com.example.questionnaire.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notice {
    private String uuid;
    private String title;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}