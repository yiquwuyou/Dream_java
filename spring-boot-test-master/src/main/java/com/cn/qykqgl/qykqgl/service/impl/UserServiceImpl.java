package com.cn.qykqgl.qykqgl.service.impl;

import com.cn.qykqgl.qykqgl.dao.UserDao;
import com.cn.qykqgl.qykqgl.entity.User;
import com.cn.qykqgl.qykqgl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public Map<String, Object> getLogin(String username,String password) {
        return userDao.getLogin( username, password);
    }

    @Override
    public List<Map> get_UserPasswordById(String id, String password) {
        return userDao.get_UserPasswordById(id,password);
    }

    @Override
    public Map<String, Object> get_UserById(String id) {
        return userDao.get_UserById(id);
    }

    @Override
    public int Update_User(User user) {
        return userDao.Update_User(user);
    }

    @Override
    public int get_IdByZjhm(String id, String zjhm) {
        return userDao.get_IdByZjhm(id,zjhm);
    }

    @Override
    public List<Map<String, Object>> find_User(String ssk) {
        return userDao.find_User(ssk);
    }

    @Override
    public int getUserByUserName(User user) {
        return userDao.getUserByUserName(user);
    }

    @Override
    public int add_User(User user) {
        return userDao.add_User(user);
    }

    @Override
    public int delete_UserById(User user) {
        return userDao.delete_UserById(user);
    }

    @Override
    public int get_UserByZjhm(String zjhm) {
        return userDao.get_UserByZjhm(zjhm);
    }

    @Override
    public int Update_UserPassword(User user) {
        return userDao.Update_UserPassword(user);
    }
}
