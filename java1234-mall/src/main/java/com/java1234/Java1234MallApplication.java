package com.java1234;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.java1234.mapper")
public class Java1234MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(Java1234MallApplication.class, args);
    }

}
