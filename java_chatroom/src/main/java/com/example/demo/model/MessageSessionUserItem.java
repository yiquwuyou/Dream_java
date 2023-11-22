package com.example.demo.model;

import lombok.Data;

// 使用这个类的对象来表示 message_session_user 表里的一个记录
@Data
public class MessageSessionUserItem {
    private int sessionId;
    private int userId;


}
