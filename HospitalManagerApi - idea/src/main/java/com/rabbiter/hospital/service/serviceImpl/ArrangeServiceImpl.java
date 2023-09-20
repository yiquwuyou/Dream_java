package com.rabbiter.hospital.service.serviceImpl;

import com.rabbiter.hospital.mapper.ArrangeMapper;
import com.rabbiter.hospital.pojo.Arrange;
import com.rabbiter.hospital.service.ArrangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;

//这段代码是 Spring Boot 项目中排班信息服务的实现类 ArrangeServiceImpl。以下是它的主要功能：
//
//        findByTime 方法：
//
//        根据日期 arTime 和科室 dSection 查询排班信息。
//        调用 arrangeMapper.findByTime(arTime, dSection) 方法，根据日期和科室查询排班信息并返回。
//        addArrange 方法：
//
//        接收一个 Arrange 对象，用于增加排班信息。
//        首先，检查是否已存在相同 arId 的排班信息，如果不存在则执行以下操作：
//        从连接池中获取 Redis 的 Jedis 实例。
//        创建一个 HashMap 对象 map，并设置一些初始值。
//        使用 Redis 的 hmset 方法将 map 存储到 Redis 中，并设置过期时间为一周（604800秒）。
//        调用 arrangeMapper.insert(arrange) 方法，将排班信息存储到数据库中。
//        返回 true，表示增加排班信息成功。
//        deleteArrange 方法：
//
//        接收排班信息的 arId。
//        首先，检查是否存在具有相同 arId 的排班信息，如果存在则执行以下操作：
//        从连接池中获取 Redis 的 Jedis 实例。
//        使用 Redis 的 del 方法删除与 arId 相关的 Redis 数据。
//        调用 arrangeMapper.deleteById(arId) 方法，从数据库中删除排班信息。
//        返回 true，表示删除排班信息成功。
//        该实现类主要负责排班信息的查询、增加和删除操作，同时利用 Redis 缓存排班信息，提高了查询效率
@Service("ArrangeService")
public class ArrangeServiceImpl implements ArrangeService {
    @Autowired
    private ArrangeMapper arrangeMapper;
    @Autowired
    private JedisPool jedisPool;//redis连接池

    /**
     * 根据日期查询排班信息
     */
    @Override
    public List<Arrange> findByTime(String arTime, String dSection) {
        return this.arrangeMapper.findByTime(arTime, dSection);
    }
    /**
     * 增加排班信息
     */
    public Boolean addArrange(Arrange arrange){
        Arrange arrange1 = this.arrangeMapper.selectById(arrange.getArId());
        Jedis jedis = jedisPool.getResource();
        HashMap<String, String> map = new HashMap<>();
        map.put("eTOn","40");
        map.put("nTOt","40");
        map.put("tTOe","40");
        map.put("fTOf","40");
        map.put("fTOs","40");
        map.put("sTOs","40");
        if (arrange1 == null) {
            //redis操作开始
//            jedis.hset(arrange.getArId(), map);
            // 或者使用hmset设置整个哈希表的值
            jedis.hmset(arrange.getArId(), map);
            jedis.expire(arrange.getArId(), 604800);
            //redis操作结束
            this.arrangeMapper.insert(arrange);
            return true;
        }
        return false;
    }

    /**
     * 删除排班信息
     */
    public Boolean deleteArrange(String arId){
        Arrange arrange = this.arrangeMapper.selectById(arId);
        Jedis jedis = jedisPool.getResource();
        if (arrange != null) {
            jedis.del(arId);
            this.arrangeMapper.deleteById(arId);
            return true;
        }
        return false;
    }

}
