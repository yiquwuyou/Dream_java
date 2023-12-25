package com.cn.qykqgl.qykqgl.controller.kqgl;

import com.cn.qykqgl.qykqgl.entity.Com;
import com.cn.qykqgl.qykqgl.service.ComService;
import com.cn.qykqgl.qykqgl.service.CzrzxxService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/com")
public class GdkqzydhController {
    @Autowired
    private ComService comService;
    @Autowired
    private CzrzxxService czrzxxService;

    /**查询各地考勤专员信息表*/
    @GetMapping("find_Com")
    @ResponseBody
    public PageInfo<Map<String,Object>> find_Com(HttpServletRequest request, String ssk){
        try {
            List<Map<String,Object>> list = null;
            PageHelper.startPage(Integer.valueOf(request.getParameter("page")),Integer.valueOf(request.getParameter("limit")));
            list = comService.find_Com(ssk);
            PageInfo info = new PageInfo(list);
            return info;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**新增或修改各地考勤专员表*/
    @PostMapping("saveOrUpdate_Com")
    @ResponseBody
    public int saveOrUpdate_Com(Com com){
        int i = 0;
        i = comService.saveOrUpdate_Com(com);
        return i;
    }

    /**通过id查询各地考勤专员表*/
    @PostMapping("getComById")
    @ResponseBody
    public Map<String,Object> getComById(String id){
        Map<String,Object> map = new HashMap<>();
        map = comService.getComById(id);
        return map;
    }

    /**通过id删除考勤表信息*/
    @GetMapping("delete_ComById")
    @ResponseBody
    public int delete_ComById(String id,String name){
        int i = 0;
        i = comService.delete_ComById(id);
        if(i>0){
            String bz = "删除考勤专员信息";
            czrzxxService.addCzrzxx(bz,name);
        }
        return i;
    }

}
