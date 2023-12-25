package com.cn.qykqgl.qykqgl.controller.rzxxgl;

import com.cn.qykqgl.qykqgl.service.CzrzxxService;
import com.cn.qykqgl.qykqgl.service.DlrzxxService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rzxx")
public class RzxxglController {
    @Autowired
    private CzrzxxService czrzxxService;
    @Autowired
    private DlrzxxService dlrzxxService;

    /**查询操作日志信息表*/
    @GetMapping("find_Czrzxx")
    @ResponseBody
    public PageInfo<Map<String,Object>> find_Czrzxx(HttpServletRequest request, String ssk){
        try {
            List<Map<String,Object>> list = null;
            PageHelper.startPage(Integer.valueOf(request.getParameter("page")),Integer.valueOf(request.getParameter("limit")));
            list = czrzxxService.find_Czrzxx(ssk);
            PageInfo info = new PageInfo(list);
            return info;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

    /**查询操作日志信息表*/
    @GetMapping("find_Dlrzxx")
    @ResponseBody
    public PageInfo<Map<String,Object>> find_Dlrzxx(HttpServletRequest request, String ssk){
        try {
            List<Map<String,Object>> list = null;
            PageHelper.startPage(Integer.valueOf(request.getParameter("page")),Integer.valueOf(request.getParameter("limit")));
            list = dlrzxxService.find_Dlrzxx(ssk);
            PageInfo info = new PageInfo(list);
            return info;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

    /**删除操作日志信息表*/
    @GetMapping("delete_Czrzxx")
    @ResponseBody
    public int delete_Czrzxx(String czyh){
        int i;
        try{
            i = czrzxxService.delete_Czrzxx();
            if(i>0){
                //给操作日志表添加信息
                String bz = "清除30天前操作日志信息";
                czrzxxService.addCzrzxx(bz,czyh);
            }
        }catch (Exception e){
            e.printStackTrace();
            i = -1;
        }
        return i;
    }

    /**删除登录日志信息表*/
    @GetMapping("delete_Dlrzxx")
    @ResponseBody
    public int delete_Dlrzxx(String czyh){
        int i;
        try{
            i = czrzxxService.delete_Dlrzxx();
            if(i>0){
                //给操作日志表添加信息
                String bz = "清除30天前登录日志信息";
                czrzxxService.addCzrzxx(bz,czyh);
            }
        }catch (Exception e){
            e.printStackTrace();
            i = -1;
        }
        return i;
    }


}
