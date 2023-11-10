package com.example.springboot2.controller;

import com.example.springboot2.model.DBType;
import com.example.springboot2.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class YmlController {
    // 读取配置文件
    @Value("${demo.key1}")
    private String key1;

    @Value("${demo.key2}")
    private String key2;

//    会自动转化配置项的类型，比如String，Integer
    @Value("${demo.key3}")
    private Integer key3;

    @RequestMapping("/readYml")
    public String readYml(){
        return key1;
    }

    @Autowired
    public Student student;

    @Autowired
    public DBType dbType;

    // 执行初始化，在属性注入后，向外开启服务前，就执行初始化方法
    @PostConstruct
    public void init(){
//        System.out.println("key1:" + key1);
//        System.out.println("key2:" + key2);
//        System.out.println("key3:" + key3);

//        System.out.println("student:" + student);
        System.out.println("dbtype:" + dbType + ", length:" + dbType.getName().length);
    }
}
