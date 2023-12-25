package com.cn.qykqgl.qykqgl.controller.jbxxgl;

import com.cn.qykqgl.qykqgl.entity.Sta;
import com.cn.qykqgl.qykqgl.service.CzrzxxService;
import com.cn.qykqgl.qykqgl.service.StaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sta")
public class StaController {
    @Autowired
    private StaService staService;
    @Autowired
    private CzrzxxService czrzxxService;

    /****
     * 查询学生表
     */
    @GetMapping("find_Sta")
    @ResponseBody
    public PageInfo<Map<String,Object>> find_Sta(HttpServletRequest request,String ssk){
        try {
            List<Map<String,Object>> list = null;
            PageHelper.startPage(Integer.valueOf(request.getParameter("page")),Integer.valueOf(request.getParameter("limit")));
            list = staService.find_Sta(ssk);
            PageInfo info = new PageInfo(list);
            return info;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /****
     * 学生表姓名和证件号码
     */
    @GetMapping("getXmAndZjhm_Sta")
    @ResponseBody
    public List<Map> getXmAndZjhm_Sta(){
        List<Map> list = null;
        list = staService.getXmAndZjhm_Sta();
        return list;
    }


    /****
     * 搜索学生表
     */
    @GetMapping("find_StaByZjhmAndXm")
    @ResponseBody
    public List<Map> find_StaByZjhmAndXm(String ssk){
        List<Map> list = null;
        list = staService.find_StaByZjhmAndXm(ssk);
        System.out.println(list);
        return list;
    }

    /**通过证件号码查询学生表*/
    @GetMapping("getStaByZjhm")
    @ResponseBody
    public int getStaByZjhm(Sta sta){
        List<Map> map = null;
        String zjhm = sta.getZjhm();
        map = staService.getStaByZjhm(zjhm);
        if (map.size()<1){
            return 1;
        }else{
            return 0;
        }
    }
    /**通过工号查询学生表*/
    @PostMapping("getStaByGh")
    @ResponseBody
    public Map<String,Object> getStaByGh(String gh){
        Map<String,Object> map = new HashMap<>();
        map = staService.getStaByGh(gh);
            return map;
    }
    /**查询部门表*/
    @GetMapping("getStaSsbm")
    @ResponseBody
    public List<Map<String,Object>> getStaSsbm(){
        List<Map<String,Object>> list = null;
        try{
            list = staService.getStaSsbm();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    /**新增或修改学生表*/
    @PostMapping("saveOrUpdate_Sta")
    @ResponseBody
    public int saveOrUpdate_Sta(Sta sta){
        int i = 0;
        System.out.println(sta);
        i = staService.saveOrUpdate_Sta(sta);
        return i;
    }
    /**删除学生*/
    @GetMapping("delete_StaByGh")
    @ResponseBody
    public int delete_StaByGh(String gh,String name){
        int i = 0;
        i = staService.delete_StaByGh(gh,name);
        if(i>0){
            String bz = "删除学生";
            System.out.println(name);
            czrzxxService.addCzrzxx(bz,name);
        }
        return i;
    }
}
