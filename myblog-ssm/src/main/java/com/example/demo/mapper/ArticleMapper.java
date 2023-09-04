package com.example.demo.mapper;


import com.example.demo.entity.ArticleInfo;
import com.example.demo.entity.vo.ArticleInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 有针对数据库的 interface（接口） ，就要有xml
// 针对用户的一个 数据持久层 的类
@Mapper
public interface ArticleMapper {

    // 添加文章
    // 具体实现的 sql语句 在xml里
    int add(ArticleInfo articleInfo);

    // id 和 uid 同时传 是因为要进行用户的校验
    // 在个人的博客页面里面，只展示属于自己的博客  ?? 好像不是。后续改注释
    ArticleInfo getDetailByIdAndUid(@Param("id") Integer id, @Param("uid") Integer uid);

    // 修改博客
    int update(ArticleInfo articleInfo);

    // 查询文章详情
    ArticleInfoVO getDetail(@Param("id") Integer id);

    // 增加阅读量
    int addRCount(@Param("id") Integer id);

    // 文章集合
    List<ArticleInfo> getListByUid(@Param("uid") Integer uid);

    int del(@Param("id") Integer id, @Param("uid") Integer uid);

    // 前端分页列表   pageSize 是每页显示最大数   offset 是 公式处理后的参数（供数据库查询用）  两者联合在数据库中进行分页查询操作
    List<ArticleInfo> getListByPage(@Param("pageSize") Integer pageSize,
                                    @Param("offset") Integer offset);

    // 获得文章总数量
    Integer getCount();
}
