package com.cn.qykqgl.qykqgl.service;


import com.cn.qykqgl.qykqgl.entity.Com;

import java.util.List;
import java.util.Map;

public interface ComService {
    List<Map<String, Object>> find_Com(String ssk);

    Map<String, Object> getComById(String id);

    int saveOrUpdate_Com(Com com);

    int delete_ComById(String id);
}
