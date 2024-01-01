package com.example.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户数据模型")
public class User {
    @ApiModelProperty("用户id")
    private int userId;
    @ApiModelProperty("用户名")
    private String username = "";
    @ApiModelProperty("密码")
    private String password = "";
    // 昵称
    private String nickname = "点击头像修改昵称哦~";
    // 头像路径
    private String avatarPath = "https://tupian.qqw21.com/article/UploadPic/2019-8/2019881731842915.jpeg";
    // 个性签名
    private String signature = "这个人很懒，什么都没有留下";
    //性别
    private String sex = "草履虫";


}
