package com.example.mybatis.mapper;


import com.example.mybatis.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoXMLMapper {
    List<UserInfo> selectAll();

    List<UserInfo> selectAll2();

    Integer insert(UserInfo userInfo);

    // 重命名后的参数使用时严格区分大小写！
    Integer insert2(@Param("userInfo") UserInfo userInfo);

    Integer delete(Integer id);

    Integer update(UserInfo userInfo);

}
