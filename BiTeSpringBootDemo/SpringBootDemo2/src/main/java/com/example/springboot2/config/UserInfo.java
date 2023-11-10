package com.example.springboot2.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class UserInfo {
    private Integer id;
    private String name;
    private Integer age;
}
