package com.example.demo.mapper;

import com.example.demo.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {

    // 获取指定会话的最后一条消息
    String getLastMessageBySessionId(int sessionId);

    // 获取指定会话历史消息列表
    // 有的会话里，历史消息可能特比特别多
    // 此处做出一个限制，默认只取最近的 100 条消息
    List<Message> getMessagesBySessionId(int sessionId);

    // 通过这个方法实现插入消息到数据库表中
    void add(Message message);
}
