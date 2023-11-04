package com.example.springboot2;

import com.example.springboot2.component.UserComponent;
import com.example.springboot2.config.BeanConfig;
import com.example.springboot2.config.UserConfig;
import com.example.springboot2.config.UserInfo;
import com.example.springboot2.controller.UController;
import com.example.springboot2.controller.UserController;
import com.example.springboot2.repo.UserRepository;
import com.example.springboot2.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan("com.example.springboot2")
@SpringBootApplication
public class SpringBoot2Application {

    public static void main(String[] args) {
        // Spring上下文
        ApplicationContext context = SpringApplication.run(SpringBoot2Application.class, args);
        // 从context中获取bean
        // 通过类型拿bean
        UserController bean = context.getBean(UserController.class);
        bean.doUserController();

//        UserService userService = context.getBean(UserService.class);
//        userService.doService();
//
//        // 通过名称拿bean
//        // 通过名称拿的话需要进行类型的转换
//        UserService userService2 = (UserService) context.getBean("userService");
//        userService2.doService();
//
//        // 根据名称和类型获取bean
//        UserService userService3 = context.getBean("userService", UserService.class);
//        userService3.doService();
//
//        //bean命名 —— 若首字母大写，则名称为小驼峰；若前两个字母都大写，则bean的名称为类名
//        UController uController = (UController) context.getBean("UController");
//        uController.doUserController();
//
//        UserRepository userRepository = context.getBean(UserRepository.class);
//        userRepository.doRepository();
//
//        UserComponent userComponent = context.getBean(UserComponent.class);
//        userComponent.doComponent();
//
//        UserConfig userConfig = context.getBean(UserConfig.class);
//        userConfig.doConfig();
//        System.out.println("userConfig:" + userConfig);
//
//        UserConfig userConfig2 = context.getBean(UserConfig.class);
//        userConfig2.doConfig();
//        System.out.println("userConfig2:" + userConfig2);
//
//        System.out.println(userConfig2 == userConfig);

        //@Bean演示
//        UserInfo userInfo = (UserInfo)context.getBean("userInfo");
//        System.out.println(userInfo);
//
//        UserInfo userInfo2 = (UserInfo)context.getBean("userInfo2", UserInfo.class);
//        System.out.println(userInfo2);
//
//        BeanConfig beanConfig = context.getBean(BeanConfig.class);
//        System.out.println(beanConfig.userInfo("666"));
//        beanConfig.userInfo2();

    }

}
