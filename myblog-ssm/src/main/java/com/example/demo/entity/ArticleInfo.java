package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

// 此实体类除了与数据库一一对应以外，还要有一个 vo 专门用来给前端数据传输的一个容器
@Data
public class ArticleInfo {
    private int id;
    private String title;
    private String content;
    // 让时间格式化，显示出咱想要的样子 此处是把时间中间的T去了
    // 现在不太了解原理，也不太会用，后续仔细了解
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createtime;
    private LocalDateTime updatetime;
    private int uid;
    private int rcount;
    private int state;
}
