package com.cn.qykqgl.qykqgl.dao;


import com.cn.qykqgl.qykqgl.entity.Sta;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface StaDao {
    List<Map<String,Object>> find_Sta(String ssk);

    int update_Sta(Sta sta);

    int save_Sta(Sta sta);

    List<Map> getStaByZjhm(String zjhm);

    Map<String, Object> getStaByGh(String gh);

    List<Map<String, Object>> getStaSsbm();

    int delete_StaByGh(String gh);

    List<Map> find_StaByZjhmAndXm(String ssk);

    List<Map> getXmAndZjhm_Sta();

    Map<String, Object> get_StaLogin(String xm, String zjhm);
}
