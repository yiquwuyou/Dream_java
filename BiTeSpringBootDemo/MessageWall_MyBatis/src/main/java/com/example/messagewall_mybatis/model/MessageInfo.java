package com.example.messagewall_mybatis.model;

import lombok.Data;

import java.util.Date;

@Data    // 组合注解，集成了@Getter @Setter @ToString 等注解
//@ToString
//@Getter
//@Setter
public class MessageInfo {
    private Integer id;
    private String from;
    private String to;
    private String message;
    private Date createTime;
    private Date updateTime;

}
