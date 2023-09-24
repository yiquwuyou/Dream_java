//这是一个挂号控制器类。
//
//        以下是这个控制器类的详细解释：
//
//        OrderController - 这是一个使用@RestController注解的控制器类，用于处理与挂号相关的请求。
//@Autowired - 使用@Autowired注解进行依赖注入，注入了OrderService。
//@RequestMapping(“order”) - 使用@RequestMapping注解，处理/order的请求。
//        updateOrder() - 这是一个方法体，接收Orders对象作为参数，调用orderService的updateOrder方法更新挂号信息。如果更新成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        updatePrice() - 这是一个方法体，接收oId作为参数，调用orderService的updatePrice方法根据挂号ID设置缴费状态。如果设置成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        findOrderFinish() - 这是一个方法体，接收pageNumber、size、query和dId作为参数，调用orderService的findOrderFinish方法根据医生ID查询已完成的挂号单信息。然后返回一个带有成功信息和查询结果的ResponseData对象。
//        findOrderByDid() - 这是一个方法体，接收pageNumber、size、query和dId作为参数，调用orderService的findOrderByDid方法根据医生ID查询挂号信息。然后返回一个带有成功信息和查询结果的ResponseData对象。
//        orderPeople() - 这是一个方法体，调用orderService的orderPeople方法统计今天的挂号人数。然后返回一个带有成功信息和统计结果的ResponseData对象。
//        orderPeopleByDid() - 这是一个方法体，接收dId作为参数，调用orderService的orderPeopleByDid方法统计今天某个医生的挂号人数。然后返回一个带有成功信息和统计结果的ResponseData对象。
//        orderSeven() - 这是一个方法体，调用orderService的orderPeople方法统计过去20天的挂号人数，并返回一个带有成功信息和统计结果的ResponseData对象。
//        orderGender() - 这是一个方法体，调用orderService的orderGender方法统计挂号男女人数，并返回一个带有成功信息和统计结果的ResponseData对象。
//        updateOrderByAdd() - 这是一个方法体，接收Orders对象作为参数，调用orderService的updateOrderByAdd方法增加诊断及医生意见。如果增加成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        findTotalPrice() - 这是一个方法体，接收oId作为参数，调用orderService的findTotalPrice方法判断诊断之后再次购买药物是否已缴费。如果未缴费，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        findOrderTime() - 这是一个方法体，接收arId作为参数，调用orderService的findOrderTime方法请求挂号时间段。然后返回一个带有成功信息和请求结果的ResponseData对象。
//        orderSection() - 这是一个方法体，调用orderService的orderSection方法统计过去20天的挂号科室人数。然后返回一个带有成功信息和统计结果的ResponseData对象。

package com.rabbiter.hospital.controller;

import com.rabbiter.hospital.pojo.Orders;
import com.rabbiter.hospital.service.OrderService;
import com.rabbiter.hospital.utils.ResponseData;
import com.rabbiter.hospital.utils.TodayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    /**
     * 根据id更新挂号信息
     */
    @PostMapping("updateOrder")
    @ResponseBody
    public ResponseData updateOrder(@RequestBody Orders orders) {
        if (this.orderService.updateOrder(orders))
            return ResponseData.success("更新挂号信息成功");

        return ResponseData.fail("更新挂号信息失败！");
    }
    /**
     * 根据id设置缴费状态
     */
    @RequestMapping("updatePrice")
    public ResponseData updatePrice(int oId){
        if (this.orderService.updatePrice(oId))
        return ResponseData.success("根据id设置缴费状态成功");
        return ResponseData.success("根据id设置缴费状态失败");
    }
    /**
     * 查找医生已完成的挂号单
     */
    @RequestMapping("findOrderFinish")
    public ResponseData findOrderFinish(int pageNumber, int size, String query, int dId){
        return ResponseData.success("查找医生已完成的挂号单完成！", this.orderService.findOrderFinish(pageNumber, size, query, dId));
    }
    /**
     * 根据dId查询挂号
     */
    @RequestMapping("findOrderByDid")
    public ResponseData findOrderByDid(int pageNumber, int size, String query, int dId){
        return ResponseData.success("返回挂号信息成功", this.orderService.findOrderByDid(pageNumber, size, query, dId)) ;
    }
    /**
     * 统计今天挂号人数
     */
    @RequestMapping("orderPeople")
    public ResponseData oderPeople(){
        String oStart = TodayUtil.getTodayYmd();
        return ResponseData.success("统计今天挂号人数成功", this.orderService.orderPeople(oStart));
    }
    /**
     * 统计今天某个医生挂号人数
     */
    @RequestMapping("orderPeopleByDid")
    public ResponseData orderPeopleByDid(int dId){
        String oStart = TodayUtil.getTodayYmd();
        return ResponseData.success("统计今天挂号人数成功", this.orderService.orderPeopleByDid(oStart, dId));
    }
    /**
     * 获取过去七天的挂号人数
     */
    @RequestMapping("orderSeven")
    public ResponseData orderSeven(){
        ArrayList<Integer> list = new ArrayList<>();
        String oStart = null;
        for(int i = 20; i > 0;i--){
            oStart = TodayUtil.getPastDate(i);
            int people = this.orderService.orderPeople(oStart);
            list.add(people);
        }
        return ResponseData.success("获取过去20天的挂号人数成功", list);
    }
    /**
     * 统计挂号男女人数
     */
    @RequestMapping("orderGender")
    public ResponseData orderGender(){
        return ResponseData.success("统计挂号男女人数", this.orderService.orderGender());
    }
    /**
     * 增加诊断及医生意见
     */
    @PostMapping("updateOrderByAdd")
    @ResponseBody
    public ResponseData updateOrderByAdd(@RequestBody Orders order){
        if (this.orderService.updateOrderByAdd(order))
            return ResponseData.success("增加诊断及医生意见成功");
        return ResponseData.fail("增加诊断及医生意见失败");
    }
    /**
     * 判断诊断之后再次购买药物是否已缴费
     */
    @RequestMapping("findTotalPrice")
    public ResponseData findTotalPrice(int oId){
       if(this.orderService.findTotalPrice(oId))
           return ResponseData.success("未缴费");
       return ResponseData.fail("无需缴费");
    }
    /**
     * 请求挂号时间段
     */
    @RequestMapping("findOrderTime")
    public ResponseData findOrderTime(String arId){
        return ResponseData.success("请求挂号时间段成功", this.orderService.findOrderTime(arId));

    }
    /**
     * 统计过去20天挂号科室人数
     */
    @RequestMapping("orderSection")
    public ResponseData orderSection(){
        return ResponseData.success("统计过去20天挂号科室人数成功", this.orderService.orderSection());
    }

}
