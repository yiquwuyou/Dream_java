package com.cn.qykqgl.qykqgl.service;


import java.util.List;
import java.util.Map;

public interface DlrzxxService {

    List<Map<String, Object>> find_Dlrzxx(String ssk);

    int add_Dlrzxx(String name);
}
