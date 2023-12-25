package com.cn.qykqgl.qykqgl.controller.kqgl;

import com.cn.qykqgl.qykqgl.entity.Check;
import com.cn.qykqgl.qykqgl.service.CheckService;
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
@RequestMapping("/check")
public class KqxxglController {
    @Autowired
    private CheckService checkService;
    @Autowired
    private CzrzxxService czrzxxService;

    /****
     * 查询考勤表
     */
    @GetMapping("find_StaAndCheck")
    @ResponseBody
    public PageInfo<Map<String,Object>> find_StaAndCheck(HttpServletRequest request, String ssk){
        try {
            List<Map<String,Object>> list = null;
            PageHelper.startPage(Integer.valueOf(request.getParameter("page")),Integer.valueOf(request.getParameter("limit")));
            list = checkService.find_StaAndCheck(ssk);
            PageInfo info = new PageInfo(list);
            return info;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /****
     * 统计考勤信息
     */
    @GetMapping("find_CheckTj")
    @ResponseBody
    public PageInfo<Map<String,Object>> find_CheckTj(HttpServletRequest request, String ssk){
        try {
            List<Map<String,Object>> list = null;
            PageHelper.startPage(Integer.valueOf(request.getParameter("page")),Integer.valueOf(request.getParameter("limit")));
            list = checkService.find_StaAndCheck(ssk);
            PageInfo info = new PageInfo(list);
            return info;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**通过id删除考勤表信息*/
    @GetMapping("delete_CheckById")
    @ResponseBody
    public int delete_CheckById(String id,String name){
        int i = 0;
        i = checkService.delete_CheckById(id,name);
        if(i>0){
            String bz = "删除考勤信息";
            czrzxxService.addCzrzxx(bz,name);
        }
        return i;
    }

    /**通过id查询考勤表*/
    @PostMapping("getCheckById")
    @ResponseBody
    public Map<String,Object> getCheckById(String id){
        Map<String,Object> map = new HashMap<>();
        map = checkService.getCheckById(id);
        return map;
    }

    /**新增或修改考勤表*/
    @PostMapping("saveOrUpdate_Check")
    @ResponseBody
    public int saveOrUpdate_Check(Check check,String name){
        int i = 0;
        try{
            i = checkService.saveOrUpdate_Check(check,name);
        }catch (Exception e){
            e.printStackTrace();
            i=0;
        }
        return i;
    }

    /**新增考勤表手动打卡*/
    @PostMapping("add_ChackByScdk")
    @ResponseBody
    public int add_ChackByScdk(Check check){
        int i = 0;
        try{
            i = checkService.add_ChackByScdk(check);
        }catch (Exception e){
            e.printStackTrace();
            i=0;
        }
        return i;
    }
    /**修改考勤表手动打卡(添加最后打卡)*/
    @PostMapping("update_ChackByXbdk")
    @ResponseBody
    public int update_ChackByXbdk(Check check){
        int i = 0;
        try{
            i = checkService.update_ChackByXbdk(check);
        }catch (Exception e){
            e.printStackTrace();
            i=0;
        }
        return i;
    }

}
