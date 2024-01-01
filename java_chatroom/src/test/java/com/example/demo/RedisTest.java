package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    // 测试redis
    @Test
    public void testRedis() {
        System.out.println(redisTemplate);
        System.out.println(redisTemplate);
        ValueOperations valueOperations = redisTemplate.opsForValue();  // 创建出可操作String类型数据的对象
        HashOperations hashOperations = redisTemplate.opsForHash();     // 创建出可操作Hash类型数据的对象
        ListOperations listOperations = redisTemplate.opsForList();     // 创建出可操作List类型数据的对象
        SetOperations setOperations = redisTemplate.opsForSet();        // 创建出可操作Set类型数据的对象
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();     // 创建出可操作ZSet类型数据的对象
    }

    /**
     *  实验userInfo相关信息的redis的设置和取出
     */
    @Test
    public void testString(){
        //hset hget hdel hkeys hvals
        HashOperations hashOperations = redisTemplate.opsForHash();
        int id = 10;
        String key1 = "key" + id;
        String username = "tom";
        String nickName_value = "点击头像修改昵称哦~";
        String avatarPath_value = "https://tupian.qqw21.com/article/UploadPic/2019-8/2019881731842915.jpeg";
        String signature_value = "这个人很懒，什么都没有留下~";
        String sex_value = "男";
        hashOperations.put(key1, "username", username);
        hashOperations.put(key1, "nickname", nickName_value);
        hashOperations.put(key1, "avatarPath", avatarPath_value);
        hashOperations.put(key1, "signature", signature_value);
        hashOperations.put(key1, "sex", sex_value);


        username = (String) hashOperations.get(key1, "username");
        System.out.println(username);

        nickName_value = (String) hashOperations.get(key1, "nickname");
        System.out.println(nickName_value);

        avatarPath_value = (String) hashOperations.get(key1, "avatarPath");
        System.out.println(avatarPath_value);

        // 试验了多种方法，无法取 value 存到set中
        // 不过可以存到 list 中
        // 以后若有时间再研究
        Set keys = hashOperations.keys(key1);
        System.out.println(keys);

        // 以后暂定都先用 list 取
        List values = hashOperations.values(key1);
        System.out.println(values);
        System.out.println(values.get(0));
        System.out.println(values.get(1));
        System.out.println(values.get(2));
        System.out.println(values.get(3));

    }

}
