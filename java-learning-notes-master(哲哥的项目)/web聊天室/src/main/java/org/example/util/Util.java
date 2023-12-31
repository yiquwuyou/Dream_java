package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.example.exception.AppException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Util {

    private static final ObjectMapper M = new ObjectMapper();

    private static final MysqlDataSource DS = new MysqlDataSource();

    static {
        //设置json序列化/反序列化的日期格式
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        M.setDateFormat(df);
        DS.setURL("jdbc:mysql://localhost:3306/java_chatroom");
        DS.setUser("root");
        DS.setPassword("111111");
        DS.setUseSSL(false);
        DS.setCharacterEncoding("UTF-8");//解决插入修改数据，如果是中文，乱码的问题
    }

    /**
     * json序列化：java对象转换为json字符串
     */
    public static String serialize(Object o){
        try {
            return M.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new AppException("json序列化失败："+o, e);
        }
    }

    /**
     * 反序列化：把json字符串转换为java对象
     */
    public static <T> T deserialize(String s, Class<T> c){
        try {
            return M.readValue(s, c);
        } catch (JsonProcessingException e) {
            //如果出现这个异常，一般都是json字符串中的键，在class中没有找到对应的属性
            throw new AppException("json反序列化失败", e);
        }
    }
    public static <T> T deserialize(InputStream is, Class<T> c){
        try {
            return M.readValue(is, c);
        } catch (IOException e) {
            //如果出现这个异常，一般都是json字符串中的键，在class中没有找到对应的属性
            throw new AppException("json反序列化失败", e);
        }
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection(){
        try {
            return DS.getConnection();
        } catch (SQLException e) {
            throw new AppException("获取数据库连接失败", e);
        }
    }

    /**
     * 释放jdbc资源
     */
    public static void close(Connection c, Statement s, ResultSet r){
        try {
            if(r != null) r.close();
            if(s != null) s.close();
            if(c != null) c.close();
        } catch (SQLException e) {
            throw new AppException("释放数据库资源出错", e);
        }
    }

    public static void close(Connection c, Statement s){
        close(c, s, null);
    }


}
