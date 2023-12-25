package com.cn.qykqgl.qykqgl.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CzrzxxDao {

    int addCzrzxx(String bz,String name);

    List<Map<String, Object>> find_Czrzxx(String ssk);

    int delete_Czrzxx();

    int delete_Dlrzxx();
}
