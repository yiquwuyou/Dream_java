package com.example.springboot2.repo;


import org.springframework.stereotype.Repository;

// 数据层、持久层就用这个注解
@Repository
public class UserRepository {
    // 代码格式化快捷键：ctrl+alt+l
    public void doRepository() {
        System.out.println("do repo...");
    }
}
