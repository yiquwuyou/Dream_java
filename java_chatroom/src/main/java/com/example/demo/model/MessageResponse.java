package com.example.demo.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

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
    // 下面是加好友操作特有的数据
    private String fromNickname;
    private String fromAvatar;
    private String toNickname;
    private String toAvatar;
    // 发送加好友时的请求信息，比如 “你好呀！我是...”
    private String requestMessage;
    private Integer isAgree;

}
