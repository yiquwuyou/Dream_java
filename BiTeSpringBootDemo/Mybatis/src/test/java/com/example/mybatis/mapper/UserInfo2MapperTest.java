package com.example.mybatis.mapper;

import com.example.mybatis.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class UserInfo2MapperTest {

    @Autowired
    private UserInfo2Mapper userInfo2Mapper;

    @Test
    void insert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("2023-2");
        userInfo.setPassword("2023pwd");
        userInfo.setAge(1);
        userInfo.setGender(1);
        userInfo.setPhone("12334566");
        userInfo2Mapper.insert(userInfo);
    }

    @Test
    void insertByXML() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("2023-2");
        userInfo.setPassword("2023");
        userInfo.setAge(1);
//        userInfo.setGender(1);
//        userInfo.setPhone("12334566");
        userInfo2Mapper.insertByXML(userInfo);
    }

    @Test
    void selectByCondition() {
        UserInfo userInfo = new UserInfo();
//        userInfo.setUsername("2023-2");
        userInfo.setGender(1);
//        userInfo.setAge(1);
        List<UserInfo> userInfos = userInfo2Mapper.selectByCondition(userInfo);
        log.info(userInfos.toString());
    }

    @Test
    void selectByCondition2() {
        UserInfo userInfo = new UserInfo();
//        userInfo.setUsername("2023-2");
//        userInfo.setGender(1);
//        userInfo.setAge(1);
        List<UserInfo> userInfos = userInfo2Mapper.selectByCondition2(userInfo);
        log.info(userInfos.toString());
    }
    @Test
    void selectByCondition3() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("2023-2");
//        userInfo.setGender(1);
//        userInfo.setAge(1);
        List<UserInfo> userInfos = userInfo2Mapper.selectByCondition3(userInfo);
        log.info(userInfos.toString());
    }

    @Test
    void updateByCondition() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("2023-9");
        userInfo.setGender(1);
//        userInfo.setAge(1);
        userInfo2Mapper.updateByCondition(userInfo);
    }
//
//    @Test
//    void updateByCondition() {
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUsername("2023-3");
////        userInfo.setGender(1);
////        userInfo.setAge(1);
//        userInfo2Mapper.updateByCondition(userInfo);
//    }
//    @Test
//    void updateByCondition2() {
//        UserInfo userInfo = new UserInfo();
////        userInfo.setUsername("2023-3");
////        userInfo.setGender(1);
////        userInfo.setAge(1);
//        userInfo2Mapper.updateByCondition2(userInfo);
//    }
//
//    @Test
//    void batchDelete() {
//
//        userInfo2Mapper.batchDelete(Arrays.asList(25,26,27,28));
//    }
}