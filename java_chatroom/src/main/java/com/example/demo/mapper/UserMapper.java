package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    // 把用户插入到数据库中 -> 注册
    int insert(User user);

    // 根据用户名查询用户信息 -> 登录
    User selectByName(String username);

    @Select("select userId from user where username = #{username}")
    Integer selectIdByUserName(String username);

    Integer updateUser(User user);
}
