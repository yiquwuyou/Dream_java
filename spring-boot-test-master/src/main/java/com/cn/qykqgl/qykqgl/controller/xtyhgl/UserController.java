package com.cn.qykqgl.qykqgl.controller.xtyhgl;


import com.cn.qykqgl.qykqgl.entity.User;
import com.cn.qykqgl.qykqgl.service.CzrzxxService;
import com.cn.qykqgl.qykqgl.service.UserService;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CzrzxxService czrzxxService;

    /**通过id查询学生表,判断旧密码是否正确*/
    @GetMapping("get_UserPasswordById")
    @ResponseBody
    public int get_UserPasswordById(User user){
        List<Map> map = null;
        System.out.println(user);
        String id =  user.getId();
        String password = user.getPassword();
        map = userService.get_UserPasswordById(id,password);
        if (map.size()>0){
            return 1;
        }else{
            return 0;
        }
    }

    /**修改密码*/
    @PostMapping("Update_UserPassword")
    @ResponseBody
    public int Update_UserPassword(User user,String czyh){
        int i = userService.Update_UserPassword(user);
        if(i>0){
            //给操作日志表添加信息
            String bz = "用户修改个人密码";
            czrzxxService.addCzrzxx(bz,czyh);
        }
        return i;
    }

    /**修改用户表信息*/
    @PostMapping("Update_User")
    @ResponseBody
    public int Update_User(User user,String czyh){
        int i = userService.Update_User(user);
        if(i>0){
            //给操作日志表添加信息
            String bz = "修改系统用户信息";
            czrzxxService.addCzrzxx(bz,czyh);
        }
        return i;
    }

    /**通过id查询用户表*/
    @PostMapping("get_UserById")
    @ResponseBody
    public Map<String,Object> get_UserById(String id){
        Map<String,Object> map = new HashMap<>();
        map = userService.get_UserById(id);
        return map;
    }

    /**通过证件号码查询用户表*/
    @PostMapping("get_UserByZjhm")
    @ResponseBody
    public int get_UserByZjhm(String id,String zjhm){
        int i = 0;
        //先判断传来的证件号码是否是自己的
        int sum = userService.get_IdByZjhm(id,zjhm);
        if(sum>0){

        }else{
            i = userService.get_UserByZjhm(zjhm);
        }
        return i;
    }

    /**通过账号查询用户表*/
    @PostMapping("getUserByUserName")
    @ResponseBody
    public int getUserByUserName(User user){
        int i = 0;
            i = userService.getUserByUserName(user);
        return i;
    }

    /**查询用户表*/
    @GetMapping("find_User")
    @ResponseBody
    public PageInfo<Map<String,Object>> find_User(HttpServletRequest request, String ssk){
        try {
            List<Map<String,Object>> list = null;
            PageHelper.startPage(Integer.valueOf(request.getParameter("page")),Integer.valueOf(request.getParameter("limit")));
            list = userService.find_User(ssk);
            PageInfo info = new PageInfo(list);
            return info;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

    /**新增用户表*/
    @PostMapping("add_User")
    @ResponseBody
    public int add_User(User user,String czyh){
        int i;
        try{
            i = userService.add_User(user);
            if(i>0){
                //给操作日志表添加信息
                String bz = "新增系统用户信息";
                czrzxxService.addCzrzxx(bz,czyh);
            }
        }catch (Exception e){
            e.printStackTrace();
            i = -1;
        }
        return i;
    }

    /**删除用户表*/
    @GetMapping("delete_UserById")
    @ResponseBody
    public int delete_UserById(User user,String czyh){
        int i;
        try{
            i = userService.delete_UserById(user);
            if(i>0){
                //给操作日志表添加信息
                String bz = "删除系统用户";
                czrzxxService.addCzrzxx(bz,czyh);
            }
        }catch (Exception e){
            e.printStackTrace();
            i = 0;
        }
        return i;
    }


}
