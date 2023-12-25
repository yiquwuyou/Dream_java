package com.cn.qykqgl.qykqgl.service.impl;

import com.cn.qykqgl.qykqgl.dao.CzrzxxDao;
import com.cn.qykqgl.qykqgl.dao.DlrzxxDao;
import com.cn.qykqgl.qykqgl.service.CzrzxxService;
import com.cn.qykqgl.qykqgl.service.DlrzxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DlrzxxServiceImpl implements DlrzxxService {
    @Autowired
    private DlrzxxDao dlrzxxDao;

    @Override
    public List<Map<String, Object>> find_Dlrzxx(String ssk) {
        return dlrzxxDao.find_Dlrzxx(ssk);
    }

    @Override
    public int add_Dlrzxx(String name) {
        return dlrzxxDao.add_Dlrzxx(name);
    }


}
