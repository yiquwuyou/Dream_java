package com.example.questionnaire.model;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Resume {
    private Integer id;
    private String username;
    private String fileName;
    private Integer fileSize;
    private String filePath;
    private String cvRemark;
    private Integer status;
    private Integer deleteFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}