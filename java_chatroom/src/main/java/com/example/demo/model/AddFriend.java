package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;
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
    private Integer isAgree;    // 0 - 拒绝   1 - 同意  2 - 未处理
    private Integer isDeleted;
//    private Date createTime;
//    private Date updateTime;

    // Date 和 LocalDateTime 都是表示日期的类，但 LocalDateTime 是线程安全的
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}