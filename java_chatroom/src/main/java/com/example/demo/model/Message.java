package com.example.demo.model;

import lombok.Data;

// 存储每条消息
@Data
public class Message {
    private int messageId;
    private int fromId;      // 表示发送者用户 id
    private String fromName; // 表示发送者的用户名
    private String fromNickname; // 表示发送者的昵称
    private String fromAvatar; // 表示发送者的头像地址
    private int sessionId;
    private String content;

}
