package com.example.mybatis.mapper;

import com.example.mybatis.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class UserInfoMapperTest {

    @Autowired
    private UserInfoMapper userInfoMapper;

//    @BeforeEach
//    void setUp() {
//        log.info("setUp");
//    }
//
//    @AfterEach
//    void tearDown() {
//        log.info("After");
//    }

    @Test
    void selectAll() {
        List<UserInfo> list = userInfoMapper.selectAll();
        log.info(list.toString());
    }

    @Test
    void selectAll2() {
        List<UserInfo> list = userInfoMapper.selectAll2();
        log.info(list.toString());
    }

    @Test
    void selectAll3() {
        List<UserInfo> list = userInfoMapper.selectAll2();
        log.info(list.toString());
    }

    @Test
    void selectOne() {
        log.info(userInfoMapper.selectOne(1).toString());
    }

    @Test
    void selectOne2() {
        log.info(userInfoMapper.selectOne(1).toString());
    }


    @Test
    void insert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("test");
        userInfo.setPassword("123456");
        userInfo.setAge(18);
        userInfo.setGender(1);
        userInfo.setPhone("123456789");
        Integer result = userInfoMapper.insert(userInfo);
        log.info("insert 方法，执行结果:{},自增ID：{}", result,userInfo.getId());
    }

    @Test
    void insert2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("test666");
        userInfo.setPassword("123456");
        userInfo.setAge(18);
        userInfo.setGender(1);
        userInfo.setPhone("123456789");
        Integer result = userInfoMapper.insert2(userInfo);
        log.info("insert 方法，执行结果:{},自增ID：{}", result,userInfo.getId());
    }

    @Test
    void delete() {
        userInfoMapper.delete(7);
    }

    @Test
    void update() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(8);
        userInfo.setId(5);
        Integer result = userInfoMapper.update(userInfo);
        if (result > 0){
            log.info("数据修改成功");
        }
    }

    @Test
    void selectByName() {
        log.info(userInfoMapper.selectByName("' or 1='1 ").toString());
    }

    @Test
    void selectUserBySort() {
        log.info(userInfoMapper.selectUserBySort("asc").toString());
    }

    @Test
    void selectUserByLike() {
        log.info(userInfoMapper.selectUserByLike("java").toString());
    }
}