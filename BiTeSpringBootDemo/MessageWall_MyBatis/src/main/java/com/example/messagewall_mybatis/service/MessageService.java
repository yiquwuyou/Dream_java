package com.example.messagewall_mybatis.service;


import com.example.messagewall_mybatis.mapper.MessageMapper;
import com.example.messagewall_mybatis.model.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    public void addMessage(MessageInfo messageInfo) {
        messageMapper.insertMessage(messageInfo);
    }

    public List<MessageInfo> getMessageInfo() {
        return messageMapper.selectAllMessage();
    }
}
