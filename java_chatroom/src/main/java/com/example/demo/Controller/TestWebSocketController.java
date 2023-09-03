package com.example.demo.Controller;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

// 先创建一个类，作为 WebSocketHandler (处理 websocket 中的各个通信流程)
// 创建一个类，继承自 TextWebSocketHandler （这个类是Spring 里内置的，提供给咱们的）
// 给这个类加上 @Component 注解，注册到 Spring 里面去
@Component
public class TestWebSocketController extends TextWebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 这个方法会在 websocket 连接建立成功后，被自动调用
        System.out.println("TestAPI 连接成功！");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 这个方法是在 websocket 收到消息的时候，被自动调用的
        System.out.println("TestAOPI 收到消息！" + message.toString());
        // session 是个会话，里面就记录了通信双方是谁（session 中就持有了 websocket 的通信连接）
        session.sendMessage(message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 这个方法是在连续出现异常的时候，被自动调用的
        System.out.println("TestAPI 连接异常！");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 这个方法是在连接正常关闭后，被自动调用的
        System.out.println("TestAPI 连接关闭！");
    }
}
