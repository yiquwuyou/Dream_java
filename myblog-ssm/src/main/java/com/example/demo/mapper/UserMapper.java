package com.example.demo.mapper;

import com.example.demo.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

// 前端搞定后，后端若需要对数据库进行操作，则顺序如下：
// 先写 mapper   然后写xml    然后写 service   最后写 controller

// 针对用户的一个 数据持久层 的类  与数据库直接通信，方法的实现放在xml里面
// mapper 相当于放函数声明，xml 里面放置函数内部的SQL语句，
// Service 里面放函数的函数体（函数内部的代码，其实主要是给函数写个返回值），最后Controller（控制器）进行函数调用
@Mapper
public interface UserMapper {

    // 注册
    int reg(UserInfo userInfo);

    // 只传一个 username ，然后拿到该对象，
    // 然后在 controller 里面对照 UserInfo 中加盐后的 password 和用户输入的 password 加盐之后是否相等
    // 用户输入的密码可以在 controller 里获得，有一个 getpassword
    // 返回一个 UserInfo 对象   更灵活
    // Param用于指定方法参数的名称 括号内部是给参数名称起的别名 此处别名和参数名称相同
    UserInfo login(@Param("username") String username);
}
