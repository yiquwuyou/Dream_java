package com.example.demo.entity;


import lombok.Data;

import java.time.LocalDateTime;

// 实体类
// 实体类通常被用于表示数据库中的表的结构，每个实例代表表中的一行记录。在业务逻辑中，开发人员可以使用实体类对数据进行处理、操作和传递。
// 实体类中的数据要和数据库表中的数据一一对应
@Data
public class UserInfo {
    private int id;
    private String username;
    private String password;
    private String photo;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;
    private int state;
}
