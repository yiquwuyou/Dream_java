//这是一个排班管理系统的后台控制器类。
//
//        以下是这个控制器类的详细解释：
//
//        ArrangeController - 这是一个使用@RestController注解的控制器类，用于处理与排班管理相关的请求。
//@Autowired - 使用@Autowired注解进行依赖注入，注入了ArrangeService。
//@RequestMapping(“/arrange”) - 使用@RequestMapping注解，处理/arrange的请求。
//        findByTime() - 这是一个方法体，接收arTime和dSection作为参数，
//        调用arrangeService的findByTime方法根据日期查询排班信息。然后将结果封装在ResponseData对象里返回。
//        addArrange() - 这是一个方法体，接收Arrange对象作为参数，
//        调用arrangeService的addArrange方法进行增加排班信息。
//        如果添加成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        deleteArrange() - 这是一个方法体，接收arId作为参数，
//        调用arrangeService的deleteArrange方法进行删除排班信息。如果删除成功，则返回一个带有成功信息的ResponseData对象；
//        否则返回一个带有失败信息的ResponseData对象。

package com.rabbiter.hospital.controller;

import com.rabbiter.hospital.pojo.Arrange;
import com.rabbiter.hospital.service.ArrangeService;
import com.rabbiter.hospital.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arrange")
public class ArrangeController {
    @Autowired
    private ArrangeService arrangeService;
    /**
     * 根据日期查询排班信息
     */
    @RequestMapping("findByTime")
    public ResponseData findByTime(@RequestParam(value = "arTime") String arTime, @RequestParam(value = "dSection") String dSection) {
        return ResponseData.success("根据日期查询排班信息成功", this.arrangeService.findByTime(arTime, dSection));
    }
    /**
     * 增加排班信息
     */
    @RequestMapping("addArrange")
    public ResponseData addArrange(Arrange arrange){
        if (this.arrangeService.addArrange(arrange))
            return ResponseData.success("增加排班信息成功");
        return ResponseData.fail("该医生该日已排班");
    }

    /**
     * 删除排班信息
     */
    @RequestMapping("deleteArrange")
    public ResponseData deleteArrange(String arId){
        if (this.arrangeService.deleteArrange(arId))
            return ResponseData.success("删除排班信息成功");
        return ResponseData.fail("排班信息不存在");
    }

}
