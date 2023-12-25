package com.cn.qykqgl.qykqgl.dao;

import com.cn.qykqgl.qykqgl.entity.Com;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ComDao {
    List<Map<String, Object>> find_Com(String ssk);

    Map<String, Object> getComById(String id);

    int save_Com(Com com);

    int update_Com(Com com);

    int delete_ComById(String id);
}
