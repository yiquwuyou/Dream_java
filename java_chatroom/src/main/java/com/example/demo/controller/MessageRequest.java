package com.example.demo.controller;

import io.swagger.annotations.ApiModel;
import lombok.Data;

// 表示一个消息请求
@Data
@ApiModel(value = "封装类，表示一条消息")
public class MessageRequest {
    private String type = "message";
    private int sessionId;
    private String content;
    private int userId;
    private String username;
    private String toUsername;

}
