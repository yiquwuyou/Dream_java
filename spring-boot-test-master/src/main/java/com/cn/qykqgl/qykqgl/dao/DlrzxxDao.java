package com.cn.qykqgl.qykqgl.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DlrzxxDao {

    List<Map<String, Object>> find_Dlrzxx(String ssk);

    int add_Dlrzxx(String name);

    int delete_Dlrzxx();
}
