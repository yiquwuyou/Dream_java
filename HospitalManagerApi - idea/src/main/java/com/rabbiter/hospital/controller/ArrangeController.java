package com.rabbiter.hospital.controller;

import com.rabbiter.hospital.pojo.Arrange;
import com.rabbiter.hospital.service.ArrangeService;
import com.rabbiter.hospital.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//这段代码是一个 Spring Boot 控制器，用于处理排班信息的相关请求。以下是它的主要功能：
//
//        findByTime 方法：
//
//        接收两个参数 arTime 和 dSection，分别表示日期和医生科室。
//        调用 arrangeService.findByTime(arTime, dSection) 方法，该方法根据给定的日期和科室查询排班信息。
//        如果查询成功，将返回包含查询结果的成功响应，消息内容为 "根据日期查询排班信息成功"。
//        如果查询失败，将返回失败响应。
//        addArrange 方法：
//
//        接收一个参数 arrange，表示要添加的排班信息。
//        调用 arrangeService.addArrange(arrange) 方法，该方法尝试添加排班信息。
//        如果添加成功，将返回成功响应，消息内容为 "增加排班信息成功"。
//        如果添加失败，将返回失败响应，消息内容为 "该医生该日已排班"。
//        deleteArrange 方法：
//
//        接收一个参数 arId，表示要删除的排班信息的唯一标识。
//        调用 arrangeService.deleteArrange(arId) 方法，该方法尝试删除排班信息。
//        如果删除成功，将返回成功响应，消息内容为 "删除排班信息成功"。
//        如果删除失败，将返回失败响应，消息内容为 "排班信息不存在"。
//        这样，控制器可以处理根据日期查询排班信息、增加排班信息和删除排班信息的请求，并根据操作结果返回相应的响应消息。
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
