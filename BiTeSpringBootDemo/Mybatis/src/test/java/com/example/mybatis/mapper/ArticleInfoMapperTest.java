package com.example.mybatis.mapper;

import com.example.mybatis.model.ArticleInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ArticleInfoMapperTest {

    @Autowired
    private ArticleInfoMapper articleInfoMapper;
    @Test
    void selectById() {
        ArticleInfo articleInfo = articleInfoMapper.selectById(1);
        log.info(articleInfo.toString());

    }

    @Test
    void selectArticleAndUserById() {
        ArticleInfo articleInfo = articleInfoMapper.selectArticleAndUserById(1);
        log.info(articleInfo.toString());
    }
}