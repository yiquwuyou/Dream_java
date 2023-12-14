package com.example.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class AddFriend {
    private Integer id;
    private Integer fromId;
    private String fromName;
    private String fromNickname;
    private String fromAvatar;
    private Integer toId;
    private String toName;
    private String toNickname;
    private String toAvatar;
    // 发送加好友时的请求信息，比如 “你好呀！我是...”
    private String requestMessage;
    private Integer isAgree;
    private Integer isDeleted;
    private Date createTime;
    private Date updateTime;
}