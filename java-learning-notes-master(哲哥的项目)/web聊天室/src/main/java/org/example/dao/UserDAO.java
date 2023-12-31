package org.example.dao;

import org.example.exception.AppException;
import org.example.model.User;
import org.example.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

public class UserDAO {

    public static User queryByName(String name) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //定义返回数据
        User u = null;
        try {
            //1 获取数据库连接Connection
            c = Util.getConnection();
            //2 通过Connection+sql创建操作命令对象Statement
            String sql = "select * from user where name=?";
            ps = c.prepareStatement(sql);
            //3 执行sql: 执行前替换占位符
            ps.setString(1, name);
            rs = ps.executeQuery();
            //4 如果是查询操作，处理结果集
            while (rs.next()){//移动到下一行，有数据返回true
                u = new User();
                //设置结果集字段到用户对象的属性中
                u.setUserId(rs.getInt("userId"));
                u.setName(name);
                u.setPassword(rs.getString("password"));
                u.setNickName(rs.getString("nickName"));
                u.setIconPath(rs.getString("iconPath"));
                u.setSignature(rs.getString("signature"));
                java.sql.Timestamp lastLogout = rs.getTimestamp("lastLogout");
                u.setLastLogout(new Date(lastLogout.getTime()));
            }
            return u;
        }catch (Exception e){
            throw new AppException("查询用户账号出错", e);
        }finally {
            //5 释放资源
            Util.close(c, ps, rs);
        }
    }

    public static int updateLastLogout(Integer userId) {
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = Util.getConnection();
            String sql = "update user set lastLogout=? where userId=?";
            ps = c.prepareStatement(sql);
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setInt(2, userId);
            return ps.executeUpdate();
        }catch (Exception e){
            throw new AppException("修改用户上次登录时间出错", e);
        }finally {
            Util.close(c, ps);
        }
    }

    public static int addUser(String name, String password, String nickName, String signature) {
        Connection c=null;
        PreparedStatement ps=null;
        try {
            c=Util.getConnection();
            String sql="insert into user values (null,?,?,?,null,?,?)";
            ps=c.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,password);
            ps.setString(3,nickName);
            ps.setString(4,signature);
            ps.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
            return ps.executeUpdate();
        }catch (Exception e){
            throw new AppException("注册失败",e);
        }finally {
            Util.close(c,ps);
        }
    }
}
