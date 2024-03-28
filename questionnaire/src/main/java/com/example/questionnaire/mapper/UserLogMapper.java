package com.example.questionnaire.mapper;

import com.example.questionnaire.model.UserLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserLogMapper {
    // 添加日志
    @Insert("insert into userlog (username, role, content) values(#{username}, #{role}, #{content})")
    void insertUserLog(String username, int role, String content);

    // 查看添加日志
    @Select("select * from userlog where role = 1 and username = #{username}")
    List<UserLog> selectAddLog(String username);

    // 查看修改日志
    @Select("select * from userlog where role = 2  and username = #{username}")
    List<UserLog> selectUpdateLog(String username);

    // 查看删除日志
    @Select("select * from userlog where role = 3  and username = #{username}")
    List<UserLog> selectDeleteLog(String username);

    // 查看我修改过谁的
    @Select("select * from userlog where role = 4 and username = #{username}")
    List<UserLog> selectUpdateMe(String username);

    // 查看谁修改过我的
    @Select("select * from userlog where role = 5 and username = #{username}")
    List<UserLog> selectUpdateByMe(String username);
    // 查看所有日志
    @Select("select * from userlog  where username = #{username}")
    List<UserLog> selectAllLog(String username);
}
