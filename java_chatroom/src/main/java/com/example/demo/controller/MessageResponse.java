package com.example.demo.controller;

import io.swagger.annotations.ApiModel;
import lombok.Data;

// 表示响应
@ApiModel(value = "表示每条消息的响应")
@Data
public class MessageResponse {
    private String type = "message";
    private Integer sessionId;
    private String content;
    private Integer fromId;
    private String fromName;
    private Integer toId;
    private String toName;
    private Integer isAgree;

}
