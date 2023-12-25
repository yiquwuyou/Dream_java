package com.cn.qykqgl.qykqgl.service;


import com.cn.qykqgl.qykqgl.entity.Check;

import java.util.List;
import java.util.Map;

public interface CheckService {
    List<Map<String, Object>> find_StaAndCheck(String ssk);

    int saveOrUpdate_Check(Check check, String name);

    int delete_CheckById(String id, String name);

    Map<String, Object> getCheckById(String id);

    int add_ChackByScdk(Check check);

    int update_ChackByXbdk(Check check);
}
