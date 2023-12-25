package com.cn.qykqgl.qykqgl.service.impl;

import com.cn.qykqgl.qykqgl.dao.CzrzxxDao;
import com.cn.qykqgl.qykqgl.dao.StaDao;
import com.cn.qykqgl.qykqgl.entity.Sta;
import com.cn.qykqgl.qykqgl.service.StaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StaServiceImpl implements StaService {
    @Autowired
    private StaDao staDao;
    @Autowired
    private CzrzxxDao czrzxxDao;

    @Override
    public List<Map<String,Object>> find_Sta(String ssk) {
        return staDao.find_Sta(ssk);
    }

    @Override
    public List<Map> find_StaByZjhmAndXm(String ssk) {
        return staDao.find_StaByZjhmAndXm(ssk);
    }

    @Override
    public List<Map> getXmAndZjhm_Sta() {
        return staDao.getXmAndZjhm_Sta();
    }

    @Override
    public Map<String, Object> get_StaLogin(String xm, String zjhm) {
        return staDao.get_StaLogin(xm,zjhm);
    }

    @Override
    public int saveOrUpdate_Sta(Sta sta) {
        System.out.println("sta.getGh():"+sta.getGh());
        if ("*".equals(sta.getGh())||"*"==sta.getGh()){
            int a =staDao.save_Sta(sta);
            if(a>0){
                czrzxxDao.addCzrzxx("新增学生信息",sta.getCjyh()+"");
            }
            return a;
        }else {
            int b =staDao.update_Sta(sta);
            if(b>0){
                czrzxxDao.addCzrzxx("修改学生信息",sta.getXgyh()+"");
            }
            return b;
        }

    }

    @Override
    public List<Map> getStaByZjhm(String zjhm) {
        return staDao.getStaByZjhm(zjhm);
    }

    @Override
    public Map<String, Object> getStaByGh(String gh) {
        return staDao.getStaByGh(gh);
    }

    @Override
    public List<Map<String, Object>> getStaSsbm() {
        return staDao.getStaSsbm();
    }

    @Override
    public int delete_StaByGh(String gh,String name) {
        int i = staDao.delete_StaByGh(gh);
        return i;
    }
}
