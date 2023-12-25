package com.cn.qykqgl.qykqgl.service;

import com.cn.qykqgl.qykqgl.entity.Sta;

import java.util.List;
import java.util.Map;

public interface StaService {

    List<Map<String,Object>> find_Sta(String ssk);

    int saveOrUpdate_Sta(Sta sta);

    List<Map> getStaByZjhm(String zjhm);

    Map<String, Object> getStaByGh(String gh);

    List<Map<String, Object>> getStaSsbm();

    int delete_StaByGh(String gh,String name);

    List<Map> find_StaByZjhmAndXm(String ssk);

    List<Map> getXmAndZjhm_Sta();

    Map<String, Object> get_StaLogin(String xm, String zjhm);
}
