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
}
