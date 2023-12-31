package com.example.java1234mailv3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.java1234mailv3.mapper")
public class Java1234MailV3Application {

    public static void main(String[] args) {
        SpringApplication.run(Java1234MailV3Application.class, args);
    }

}
