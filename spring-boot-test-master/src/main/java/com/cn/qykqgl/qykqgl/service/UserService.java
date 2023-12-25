package com.cn.qykqgl.qykqgl.service;


import com.cn.qykqgl.qykqgl.entity.User;

import java.util.List;
import java.util.Map;
public interface UserService {

    Map<String,Object> getLogin(String username,String password);


    int Update_UserPassword(User user);

    int get_UserByZjhm(String zjhm);

    List<Map> get_UserPasswordById(String id, String password);

    Map<String, Object> get_UserById(String id);

    int Update_User(User user);

    int get_IdByZjhm(String id,String zjhm);

    List<Map<String, Object>> find_User(String ssk);

    int getUserByUserName(User user);

    int add_User(User user);

    int delete_UserById(User user);
}
