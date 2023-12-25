package com.cn.qykqgl.qykqgl.service.impl;

import com.cn.qykqgl.qykqgl.dao.CheckDao;
import com.cn.qykqgl.qykqgl.dao.CzrzxxDao;
import com.cn.qykqgl.qykqgl.entity.Check;
import com.cn.qykqgl.qykqgl.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CheckServiceImpl implements CheckService {
    @Autowired
    private CheckDao checkDao;
    @Autowired
    private CzrzxxDao czrzxxDao;

    @Override
    public List<Map<String, Object>> find_StaAndCheck(String ssk) {
        return checkDao.find_StaAndCheck(ssk);
    }

    @Override
    public int saveOrUpdate_Check(Check check,String name) {
        if ("*".equals(check.getId())||"*"==check.getId()){
            int a =checkDao.save_Check(check);
            if(a>0){
                czrzxxDao.addCzrzxx("新增考勤打卡信息",name);
            }
            return a;
        }else {
            int b =checkDao.update_Check(check);
            if(b>0){
                czrzxxDao.addCzrzxx("修改考勤打卡信息",name);
            }
            return b;
        }
    }

    @Override
    public int delete_CheckById(String id, String name) {
        int i = checkDao.delete_CheckById(id);
        return i;
    }

    @Override
    public Map<String, Object> getCheckById(String id) {
        return checkDao.getCheckById(id);
    }

    @Override
    public int add_ChackByScdk(Check check) {
        return checkDao.add_ChackByScdk(check);
    }

    @Override
    public int update_ChackByXbdk(Check check) {
        return checkDao.update_ChackByXbdk(check);
    }
}
