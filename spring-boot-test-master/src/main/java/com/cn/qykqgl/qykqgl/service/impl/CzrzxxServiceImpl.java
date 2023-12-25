package com.cn.qykqgl.qykqgl.service.impl;

import com.cn.qykqgl.qykqgl.dao.CzrzxxDao;
import com.cn.qykqgl.qykqgl.service.CzrzxxService;
import com.cn.qykqgl.qykqgl.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CzrzxxServiceImpl implements CzrzxxService {
    @Autowired
    private CzrzxxDao czrzxxDao;
    @Override
    public int addCzrzxx(String bz, String name) {
        return czrzxxDao.addCzrzxx(bz,name);
    }

    @Override
    public List<Map<String, Object>> find_Czrzxx(String ssk) {
        return czrzxxDao.find_Czrzxx(ssk);
    }

    @Override
    public int delete_Czrzxx() {
        return czrzxxDao.delete_Czrzxx();
    }

    @Override
    public int delete_Dlrzxx() {
        return czrzxxDao.delete_Dlrzxx();
    }
}
