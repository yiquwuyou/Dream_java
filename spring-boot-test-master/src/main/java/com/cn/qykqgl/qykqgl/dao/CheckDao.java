package com.cn.qykqgl.qykqgl.dao;

import com.cn.qykqgl.qykqgl.entity.Check;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CheckDao {
    List<Map<String, Object>> find_StaAndCheck(String ssk);

    int save_Check(Check check);

    int update_Check(Check check);

    Map<String, Object> getCheckById(String id);

    int delete_CheckById(String id);

    int add_ChackByScdk(Check check);

    int update_ChackByXbdk(String scdk, String zhdk, String ryzj);

    int update_ChackByXbdk(Check check);
}
