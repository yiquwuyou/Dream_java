package com.rabbiter.hospital.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.hospital.mapper.OrderMapper;
import com.rabbiter.hospital.pojo.Orders;
import com.rabbiter.hospital.service.OrderService;
import com.rabbiter.hospital.utils.RandomUtil;
import com.rabbiter.hospital.utils.TodayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

//这段代码是挂号信息服务的实现类 OrderServiceImpl，它处理了挂号信息的增加、查询、更新、删除等操作。以下是该类的主要功能：
//
//        findAllOrders 方法：
//
//        分页模糊查询所有挂号信息。
//        使用 MyBatis Plus 的 Page 类和 QueryWrapper 构建查询条件，支持分页查询和模糊查询。
//        返回查询结果和分页信息。
//        deleteOrder 方法：
//
//        根据挂号的 oId 删除挂号信息。
//        使用 MyBatis Plus 的 orderMapper.deleteById(oId) 方法，根据挂号的 oId 删除挂号信息。
//        返回 true，表示删除挂号信息成功。
//        addOrder 方法：
//
//        增加挂号信息。
//        先使用 Redis 进行挂号时间段的预约数量检查。
//        如果预约数量足够，就减少预约数量，然后插入挂号信息到数据库中。
//        返回 true 表示增加挂号信息成功，返回 false 表示预约数量不足。
//        findOrderByPid 方法：
//
//        根据患者的 pId 查询挂号信息。
//        使用 MyBatis Plus 的 orderMapper.findOrderByPid(pId) 方法，根据患者的 pId 查询挂号信息。
//        findOrderByNull 方法：
//
//        查看当天挂号列表，根据医生的 dId 和挂号时间 oStart 查询。
//        使用 MyBatis Plus 的 orderMapper.findOrderByNull(dId, oStart) 方法，根据医生的 dId 和挂号时间 oStart 查询当天的挂号列表。
//        updateOrder 方法：
//
//        根据挂号的 oId 更新挂号信息，将挂号状态设置为已完成。
//        使用 MyBatis Plus 的 orderMapper.update 方法，更新挂号信息。
//        返回 true 表示更新挂号信息成功。
//        updatePrice 方法：
//
//        根据挂号的 oId 设置挂号的缴费状态。
//        使用 MyBatis Plus 的 UpdateWrapper 构建更新条件，将挂号的 oPriceState 设置为已缴费。
//        返回 true 表示设置缴费状态成功。
//        findOrderFinish 方法：
//
//        查找医生已完成的挂号单，根据医生的 dId 查询。
//        使用 MyBatis Plus 的 QueryWrapper 构建查询条件，包括模糊查询、医生的 dId 和挂号状态为已完成的条件。
//        返回查询结果和分页信息。
//        findOrderByDid 方法：
//
//        根据医生的 dId 查询挂号信息。
//        使用 MyBatis Plus 的 QueryWrapper 构建查询条件，包括模糊查询和医生的 dId。
//        返回查询结果和分页信息。
//        orderPeople 和 orderPeopleByDid 方法：
//
//        统计挂号人数，分别根据挂号时间 oStart 和医生的 dId 统计。
//        使用 MyBatis Plus 的 orderMapper.orderPeople(oStart) 方法和 orderMapper.orderPeopleByDid(oStart, dId) 方法，统计挂号人数。
//        orderGender 方法：
//
//        统计挂号男女人数。
//        使用 MyBatis Plus 的 orderMapper.orderGender() 方法，统计挂号男女人数。
//        updateOrderByAdd 方法：
//
//        增加诊断及医生意见。
//        使用 MyBatis Plus 的 orderMapper.updateOrderByAdd(order) 方法，更新挂号信息。
//        findTotalPrice 方法：
//
//        判断诊断之后再次购买药物是否已缴费。
//        根据挂号的 oId 查询挂号信息，如果总价格 OTotalPrice 不为零，将缴费状态 OPriceState 设置为未缴费。
//        返回 true 表示需要缴费，返回 false 表示无需缴费。
//        findOrderTime 方法：
//
//        请求挂号时间段。
//        使用 Redis 查询挂号时间段的预约数量。
//        orderSection 方法：
//
//        统计过去20天挂号科室人数。
//        使用 MyBatis Plus 的 orderMapper.orderSection(startTime, endTime) 方法，统计过去20天挂号科室人数。
//        这个实现类主要负责挂号信息的管理和查询，包括挂号信息的增加、查询、删除、更新等操作，并且使用了 Redis 进行挂号时间段的预约数量控制
@Service("OrderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Autowired
    private JedisPool jedisPool;//redis连接池
    /**
     * 分页模糊查询所有挂号信息
     */
    @Override
    public HashMap<String, Object> findAllOrders(int pageNumber, int size, String query) {
        Page<Orders> page = new Page<>(pageNumber, size);
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.like("p_id", query);
        IPage<Orders> iPage = this.orderMapper.selectPage(page, wrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("pages", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("orders", iPage.getRecords()); //查询到的记录
        return hashMap;
    }

    /**
     * 删除挂号信息
     */
    @Override
    public Boolean deleteOrder(int oId) {
        this.orderMapper.deleteById(oId);
        return true;
    }
    /**
     * 增加挂号信息
     */
    @Override
    public Boolean addOrder(Orders order, String arId){
        //redis开始
        Jedis jedis = jedisPool.getResource();
        String time = order.getOStart().substring(11, 22);
        synchronized (this) {
            if (time.equals("08:30-09:30")) {
                if (jedis.hget(arId, "eTOn").equals("0"))
                    return false;
                jedis.hincrBy(arId, "eTOn", -1);
            }

            if (time.equals("09:30-10:30")) {
                if (jedis.hget(arId, "nTOt").equals("0"))
                    return false;
                jedis.hincrBy(arId, "nTOt", -1);
            }
            if (time.equals("10:30-11:30")) {
                if (jedis.hget(arId, "tTOe").equals("0"))
                    return false;
                jedis.hincrBy(arId, "tTOe", -1);
            }
            if (time.equals("14:30-15:30")) {
                if (jedis.hget(arId, "fTOf").equals("0"))
                    return false;
                jedis.hincrBy(arId, "fTOf", -1);
            }
            if (time.equals("15:30-16:30")) {
                if (jedis.hget(arId, "fTOs").equals("0"))
                    return false;
                jedis.hincrBy(arId, "fTOs", -1);
            }
            if (time.equals("16:30-17:30")) {
                if (jedis.hget(arId, "sTOs").equals("0"))
                    return false;
                jedis.hincrBy(arId, "sTOs", -1);
            }
        }
        jedis.close();
        //redis结束
        order.setOId(RandomUtil.randomOid(order.getPId()));
        order.setOState(0);
        order.setOPriceState(0);
        order.setOStart(order.getOStart().substring(0,22));
        this.orderMapper.insert(order);
        return true;
    }
    /**
     * 根据pId查询挂号
     */
    public List<Orders> findOrderByPid(int pId){

        return this.orderMapper.findOrderByPid(pId);
    }
    /**
     * 查看当天挂号列表
     */
    @Override
    public List<Orders> findOrderByNull(int dId, String oStart){
        return this.orderMapper.findOrderByNull(dId, oStart);
    }
    /**
     * 根据id更新挂号信息
     */
    @Override
    public Boolean updateOrder(Orders orders) {
        orders.setOState(1);
        orders.setOEnd(TodayUtil.getToday());
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("o_id", orders.getOId());
        this.orderMapper.update(orders, wrapper);
        return true;
    }
    /**
     * 根据id设置缴费状态
     */
    @Override
    public Boolean updatePrice(int oId){
        /**
         * 用QueryWrapper如果不把外键的值也传进来，会报错
         * 用UpdateWrapper就正常
         */
        UpdateWrapper<Orders> wrapper = new UpdateWrapper<>();
        wrapper.eq("o_id", oId).set("o_price_state", 1).set("o_total_price", 0.00);
        int i = this.orderMapper.update(null, wrapper);
        System.out.println("影响行数"+i);
        return true;
    }
    /**
     * 查找医生已完成的挂号单
     */
    @Override
    public HashMap<String, Object> findOrderFinish(int pageNumber, int size, String query, int dId){
        Page<Orders> page = new Page<>(pageNumber, size);
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.like("p_id", query).eq("d_id", dId).orderByDesc("o_start").eq("o_state", 1);
        IPage<Orders> iPage = this.orderMapper.selectPage(page, wrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("pages", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("orders", iPage.getRecords()); //查询到的记录

        return hashMap;
    }
    /**
     * 根据dId查询挂号
     */
    public HashMap<String, Object> findOrderByDid(int pageNumber, int size, String query, int dId){
        Page<Orders> page = new Page<>(pageNumber, size);
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.like("p_id", query).eq("d_id", dId).orderByDesc("o_start");
        IPage<Orders> iPage = this.orderMapper.selectPage(page, wrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("pages", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("orders", iPage.getRecords()); //查询到的记录
        return hashMap;
    }
    /**
     * 统计今天挂号人数
     */
    @Override
    public int orderPeople(String oStart){
        return this.orderMapper.orderPeople(oStart);
    }
    /**
     * 统计今天某个医生挂号人数
     */
    @Override
    public int orderPeopleByDid(String oStart, int dId){
        return this.orderMapper.orderPeopleByDid(oStart, dId);
    }
    /**
     * 统计挂号男女人数
     */
    public List<String> orderGender(){
        return this.orderMapper.orderGender();
    }
    /**
     * 增加诊断及医生意见
     */
    public Boolean updateOrderByAdd(Orders order){

        if (this.orderMapper.updateOrderByAdd(order) == 0){
            return false;
        }

        return true;
    }
    /**
     * 判断诊断之后再次购买药物是否已缴费
     */
    public Boolean findTotalPrice(int oId){
        Orders order = this.orderMapper.selectById(oId);
        if (order.getOTotalPrice() != 0.00){
            order.setOPriceState(0);
            this.orderMapper.updateById(order);
            return true;
        }
        return false;
    }
    /**
     * 请求挂号时间段
     */
    @Override
    public HashMap<String, String> findOrderTime(String arId){
        Jedis jedis = jedisPool.getResource();
        return (HashMap<String, String>) jedis.hgetAll(arId);
    }
    /**
     * 统计过去20天挂号科室人数
     */
    @Override
    public List<String> orderSection(){
        String startTime = TodayUtil.getPastDate(20);
        String endTime = TodayUtil.getTodayYmd();
        return this.orderMapper.orderSection(startTime, endTime);
    }
}
