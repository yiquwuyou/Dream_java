package com.cn.qykqgl.qykqgl.dao;

import com.cn.qykqgl.qykqgl.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    Map<String, Object> getLogin(String username,String password);

    List<Map> get_UserPasswordById(String id, String password);

    int Update_UserPassword(User user);

    Map<String, Object> get_UserById(String id);

    int get_UserByZjhm(String zjhm);

    int get_IdByZjhm(String id,String zjhm);

    int Update_User(User user);

    List<Map<String, Object>> find_User(String ssk);

    int getUserByUserName(User user);

    int add_User(User user);

    int delete_UserById(User user);
}
