package com.example.questionnaire.model;

import lombok.Data;

import java.time.LocalDateTime;

// 下面都是些默认的值
@Data
public class User {
    private Integer userId;
    private String username = "";
    private String password = "";
    // 昵称
    private String nickname = "点击头像修改昵称哦~";
    // 头像路径
    private String avatarPath = "https://tupian.qqw21.com/article/UploadPic/2019-8/2019881731842915.jpeg";
    // 个性签名
    private String signature = "这个人很懒，什么都没有留下";
    //性别
    private String sex = "男";
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
