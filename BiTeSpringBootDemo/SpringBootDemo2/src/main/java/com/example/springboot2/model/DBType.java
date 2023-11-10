package com.example.springboot2.model;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

// yml配置集合
@Component
@ConfigurationProperties(prefix = "dbtypes")
@Data
public class DBType {

//    private List<String> name;
    // 使用数组接收也是ok的
    private String[] name;

    // 接收map
    private HashMap<String, String> map;
}
