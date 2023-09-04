package com.example.demo.service;


import com.example.demo.entity.ArticleInfo;
import com.example.demo.entity.vo.ArticleInfoVO;
import com.example.demo.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public int add(ArticleInfo articleInfo){
        return articleMapper.add(articleInfo);
    }

    public ArticleInfo getDetailByIdAndUid(Integer id, Integer uid) {
        return articleMapper.getDetailByIdAndUid(id, uid);
    }

    public int update(ArticleInfo articleInfo) {
        return articleMapper.update(articleInfo);
    }

    public ArticleInfoVO getDetail(Integer id) {
        return articleMapper.getDetail(id);
    }

    public int addRCount(Integer id) {
        return articleMapper.addRCount(id);
    }

    public List<ArticleInfo> getListByUid(Integer uid) {
        return articleMapper.getListByUid(uid);
    }

    public int del(Integer id, Integer uid) {
        return articleMapper.del(id, uid);
    }

    public List<ArticleInfo> getListByPage(Integer pageSize, Integer offset) {
        return articleMapper.getListByPage(pageSize, offset);
    }

    public Integer getCount() {
        return articleMapper.getCount();
    }
}
