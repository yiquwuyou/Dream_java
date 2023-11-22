package com.example.demo.model;

import lombok.Data;

import java.util.List;

// 使用这个类表示一个会话
@Data
public class MessageSession {
    private int sessionId;
    private List<Friend> friends;
    private String lastMessage;

}
