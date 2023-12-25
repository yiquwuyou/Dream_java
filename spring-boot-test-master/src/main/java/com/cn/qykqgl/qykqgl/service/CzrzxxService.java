package com.cn.qykqgl.qykqgl.service;


import java.util.List;
import java.util.Map;

public interface CzrzxxService {

    int addCzrzxx(String bz, String name);

    List<Map<String, Object>> find_Czrzxx(String ssk);

    int delete_Czrzxx();

    int delete_Dlrzxx();
}
