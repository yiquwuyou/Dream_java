package com.example.mybatis.mapper;


import com.example.mybatis.model.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 动态SQL练习
 */
@Mapper
public interface UserInfo2Mapper {
//    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("<script>" +
            "insert into userinfo (username,password,age," +
            "<if test='gender!=null' >" +
            "gender,</if>" +
            "phone)" +
            "values (#{username},#{password},#{age}," +
            "<if test='gender!=null' >#{gender},</if>" +
            "#{phone})" +
            "</script>")
    Integer insert(UserInfo userInfo);

    Integer insertByXML(UserInfo userInfo);

    /**
     * 使用where标签
     * @param userInfo
     * @return
     */
    List<UserInfo> selectByCondition(UserInfo userInfo);

    /**
     * 使用trim标签
     * @param userInfo
     * @return
     */
    List<UserInfo> selectByCondition2(UserInfo userInfo);

    /**
     * 注解的方式
     * 这种方式script标签下面的部分几乎和xml的一摸一样
     * @param userInfo
     * @return
     */
    @Select("<script>" +
            "select * from userinfo" +
            "        <where>" +
            "            <if test='username!=null'>" +
            "                username = #{username}" +
            "            </if>" +
            "            <if test='age!=null'>" +
            "                and age = #{age}" +
            "            </if>" +
            "            <if test='gender!=null'>" +
            "                and gender = #{gender}" +
            "            </if>" +
            "        </where>" +
            "</script>")
    List<UserInfo> selectByCondition3(UserInfo userInfo);

    Integer updateByCondition(UserInfo userInfo);
}
