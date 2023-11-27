package com.example.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class AddFriend {
    private Integer id;
    private Integer fromId;
    private String fromName;
    private Integer toId;
    private String toName;
    private Integer isAgree;
    private Integer isDelete;
    private Date createTime;
    private Date updateTime;
}