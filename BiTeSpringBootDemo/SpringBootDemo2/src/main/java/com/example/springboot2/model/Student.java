package com.example.springboot2.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


// yml配置对象
@Component
@ConfigurationProperties(prefix = "student")
@Data
public class Student {
    private Integer id;
    private String name;
    private Integer age;
}
