package com.example.springboot2;

import com.example.springboot2.config.UserInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan("com.example.springboot2")
@SpringBootApplication
public class SpringBoot2Application {

    public static void main(String[] args) {
        // 获取Spring上下文对象
        ApplicationContext context = SpringApplication.run(SpringBoot2Application.class, args);
        // 从context(Spring上下文)中获取bean
        // 通过类型拿bean
//        UserController userController = context.getBean(UserController.class);
//        userController.doUserController();
        // 通过Bean名(对象名)取对象
//        UserController userController2 = (UserController) context.getBean("userController");
//        userController2.doUserController();
//        // 通过Bean名和类型加在一起取对象
//        UserController userController3 = context.getBean("userController",UserController.class);
//        userController3.doUserController();

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
////
//        UserComponent userComponent = context.getBean(UserComponent.class);
//        userComponent.doComponent();
////
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
//        UserInfo userInfo = context.getBean(UserInfo.class);
//        System.out.println(userInfo);
////
//        UserInfo userInfo2 = context.getBean(UserInfo.class);
//        System.out.println(userInfo2);
//
//        BeanConfig beanConfig = context.getBean(BeanConfig.class);
//        System.out.println(beanConfig.userInfo("666"));
//        beanConfig.userInfo2();

    }

}
