package com.example.demo.entity.vo;

import com.example.demo.entity.ArticleInfo;
import lombok.Data;

// 继承 ArticleInfo 的所有信息
@Data
public class ArticleInfoVO extends ArticleInfo {
    private String username;
}
