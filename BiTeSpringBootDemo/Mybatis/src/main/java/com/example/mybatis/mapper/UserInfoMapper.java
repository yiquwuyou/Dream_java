package com.example.mybatis.mapper;


import com.example.mybatis.model.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    /**
     * 结果映射
     * 方法一：对字段进行重命名
     * @return
     */
    // 注意换行的话，前后两个字符串中间是否用空格
    @Select("select id, username, password, age, gender, phone, " +
            "delete_flag as deleteFlag, create_time as createTime, update_time as updateTime " +
            " from userinfo")
    List<UserInfo> selectAll();


    /**
     * 结果映射
     * 方法二：使用注解
     * @return
     */
    @Results(id = "BaseMap", value = {
            // column是数据库中的列名(字段名)， property是对象中的属性名
            @Result(column = "delete_flag", property = "deleteFlag"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("select id, username, password, age, gender, phone, " +
            "delete_flag , create_time , update_time  " +
            " from userinfo")
//    @Select("select * from userinfo")
    List<UserInfo> selectAll2();

    // 注意：上面的操作这样写，就取不到值
//    @Results({
//            // column是数据库中的列名(字段名)， property是对象中的属性名
//            @Result(column = "delete_flag", property = "deleteFlag"),
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
//    @Select("select id, username, password, age, gender, phone, " +
//            "delete_flag as deleteFlag, create_time as createTime, update_time as updateTime " +
//            " from userinfo")
//    List<UserInfo> selectAll2();

    // 复用 @Results
    @ResultMap(value = "BaseMap")
    @Select("select * from userinfo where id = #{id}")
    UserInfo selectOne(Integer id);

    @ResultMap(value = "BaseMap")
    @Select("select * from userinfo where username = #{username}")
//    UserInfo selectByName(String username);
    List<UserInfo> selectByName(String username);


    /**
     * 结果映射
     * 方法三：使用注解的方式，自动转驼峰
     * @return
     */
    @Select("select * from userinfo")
    List<UserInfo> selectAll3();

    @Select("select * from userinfo where id = #{userId}")
    UserInfo selectOne2(@Param("userId") Integer id);

    // 如果太长了，用+号完成字符串的拼接
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into userinfo (username,password,age,gender,phone)" +
            "values (#{username},#{password},#{age},#{gender},#{phone})")
    Integer insert(UserInfo userInfo);

    // 对Insert插入参数重命名
    // 如果重命名了，就不能再直接获取对应属性值了，应该对象.属性来获取
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into userinfo (username,password,age,gender,phone)" +
            "values (#{userInfo.username},#{userInfo.password},#{userInfo.age},#{userInfo.gender},#{userInfo.phone})")
    Integer insert2(@Param("userInfo") UserInfo userInfo);

    @Delete("delete from userinfo where id=#{id}")
    Integer delete(Integer id);

    @Update("update userinfo set age=#{age} where id=#{id}")
    Integer update(UserInfo userInfo);


    /**
     * 排序
     * @param sort
     * @return
     */
    @Select("select * from userinfo order by id ${sort}")
    List<UserInfo> selectUserBySort(String sort);

    /**
     * 模糊查询
     * @param username
     * @return
     */
//    @Select("select * from userinfo where username like '%${username}%'")
    // 防止SQL注入的问题，所以这样写！
    @Select("select * from userinfo where username like concat('%',#{username},'%')")
    List<UserInfo> selectUserByLike(String username);
}
