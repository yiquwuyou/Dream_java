//这段代码是一个Spring Boot应用程序的启动类，主要包含以下内容：
//
//        使用注解@MapperScan指定了扫描Mapper接口的包路径，其中 “com.rabbiter.hospital.mapper” 是需要扫描的Mapper接口所在的包。
//        使用注解@SpringBootApplication标识该类为Spring Boot应用程序的入口类。
//        在main方法中，使用SpringApplication.run方法启动Spring Boot应用程序，传入HospitalManagerApplication.class作为主要的配置类，args为启动参数。
//        通过以上配置，可以启动Spring Boot应用程序，并自动扫描Mapper接口，并将其注册为Spring的Bean。

package com.rabbiter.hospital;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.rabbiter.hospital.mapper")
@SpringBootApplication
public class HospitalManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalManagerApplication.class, args);
    }

}
