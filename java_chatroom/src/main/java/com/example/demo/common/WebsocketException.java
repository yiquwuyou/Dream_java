package com.example.demo.common;

/**
 *  自定义一个异常类，如果WebSocket异常断开，就抛出这个异常，同时在构造方法中指定出现异常的原因信息
 */
public class WebsocketException extends Exception{
    public WebsocketException(String reason){
        super(reason);
    }
}
