package com.example.questionnaire.mapper;

import com.example.questionnaire.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    // 把用户插入到数据库中 -> 注册
    Integer insertuser(User user);

    // 根据用户名查询用户信息 -> 登录
    @Select("select * from user where username = #{username}")
    User selectByName(String username);

    @Select("select userId from user where username = #{username}")
    Integer selectIdByUserName(String username);

    Integer updateUser(User user);

    // 查询密码
    @Select("select password from user where username = #{username}")
    String selectPassword(String username);
    // 修改密码
    @Update("update user set password = #{password} where username = #{username}")
    Integer updatePassword(String username, String password);
}
