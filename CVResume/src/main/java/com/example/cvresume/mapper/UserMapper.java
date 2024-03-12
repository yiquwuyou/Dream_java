package com.example.cvresume.mapper;

import com.example.cvresume.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    // 把用户插入到数据库中 -> 注册
    int insertuser(User user);

    // 根据用户名查询用户信息 -> 登录
    @Select("select * from user where username = #{username}")
    User selectByName(String username);

    @Select("select userId from user where username = #{username}")
    Integer selectIdByUserName(String username);

    Integer updateUser(User user);
}
