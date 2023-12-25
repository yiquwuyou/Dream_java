package com.cn.qykqgl.qykqgl.service.impl;

import com.cn.qykqgl.qykqgl.dao.ComDao;
import com.cn.qykqgl.qykqgl.dao.CzrzxxDao;
import com.cn.qykqgl.qykqgl.entity.Com;
import com.cn.qykqgl.qykqgl.service.ComService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ComServiceImpl  implements ComService {
    @Autowired
    private ComDao comDao;
    @Autowired
    private CzrzxxDao czrzxxDao;

    @Override
    public List<Map<String, Object>> find_Com(String ssk) {
        return comDao.find_Com(ssk);
    }

    @Override
    public Map<String, Object> getComById(String id) {
        return comDao.getComById(id);
    }

    @Override
    public int saveOrUpdate_Com(Com com) {
        if ("*".equals(com.getId())||"*"==com.getId()){
            int a = comDao.save_Com(com);
            if(a>0){
                czrzxxDao.addCzrzxx("新增考勤专员电话信息",com.getGh()+"");
            }
            return a;
        }else {
            int b = comDao.update_Com(com);
            if(b>0){
                czrzxxDao.addCzrzxx("修改考勤专员电话信息",com.getGh()+"");
            }
            return b;
        }
    }

    @Override
    public int delete_ComById(String id) {
        return comDao.delete_ComById(id);
    }
}
