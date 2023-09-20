package com.rabbiter.hospital.controller;

import com.rabbiter.hospital.pojo.Bed;
import com.rabbiter.hospital.service.BedService;
import com.rabbiter.hospital.utils.ResponseData;
import com.rabbiter.hospital.utils.TodayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//这段代码是一个 Spring Boot 控制器，用于处理床位信息的相关请求。以下是它的主要功能：
//
//        findNullBed 方法：
//
//        无参数，用于查找所有空床位。
//        调用 bedService.findNullBed() 方法，该方法查询所有空床位信息。
//        如果查询成功，将返回包含查询结果的成功响应，消息内容为 "查找所有空床位成功"。
//        如果查询失败，将返回失败响应。
//        updateBed 方法：
//
//        接收一个参数 bed，表示要更新的床位信息。
//        调用 bedService.updateBed(bed) 方法，该方法尝试更新床位信息。
//        如果更新成功，将返回成功响应，消息内容为 "增加床位成功！"。
//        如果更新失败，将返回失败响应，消息内容为 "增加床位失败！"。
//        findBedByPid 方法：
//
//        接收一个参数 pId，表示要根据患者ID查询住院信息。
//        调用 bedService.findBedByPid(pId) 方法，该方法根据患者ID查询住院信息。
//        如果查询成功，将返回包含查询结果的成功响应，消息内容为 "根据pId查询住院成功"。
//        如果查询失败，将返回失败响应。
//        findAllBeds 方法：
//
//        接收三个参数 pageNumber、size 和 query，用于分页模糊查询所有床位信息。
//        调用 bedService.findAllBeds(pageNumber, size, query) 方法，该方法根据分页和查询条件查询床位信息。
//        如果查询成功，将返回包含查询结果的成功响应，消息内容为 "返回所有床位信息成功"。
//        如果查询失败，将返回失败响应。
//        findBed 方法：
//
//        接收一个参数 bId，表示要根据床位ID查找床位信息。
//        调用 bedService.findBed(bId) 方法，该方法根据床位ID查找床位信息。
//        如果查询成功，将返回包含查询结果的成功响应，消息内容为 "根据id查找床位成功"。
//        如果查询失败，将返回失败响应。
//        addBed 方法：
//
//        接收一个参数 bed，表示要添加的床位信息。
//        调用 bedService.addBed(bed) 方法，该方法尝试添加床位信息。
//        如果添加成功，将返回成功响应，消息内容为 "增加床位信息成功"。
//        如果添加失败，将返回失败响应，消息内容为 "增加床位信息失败！床号或已被占用"。
//        deleteBed 方法：
//
//        接收一个参数 bId，表示要删除的床位信息的唯一标识。
//        调用 bedService.deleteBed(bId) 方法，该方法尝试删除床位信息。
//        如果删除成功，将返回成功响应，消息内容为 "删除床位信息成功"。
//        如果删除失败，将返回失败响应，消息内容为 "删除床位信息失败"。
//        emptyBed 方法：
//
//        接收一个参数 bId，表示要清空的床位信息的唯一标识。
//        调用 bedService.emptyBed(bId) 方法，该方法尝试清空床位信息。
//        如果清空成功，将返回成功响应，消息内容为 "清空床位信息成功"。
//        如果清空失败，将返回失败响应，消息内容为 "清空床位信息失败"。
//        bedPeople 方法：
//
//        无参数，用于统计今天的住院人数。
//        获取今天的日期，并调用 bedService.bedPeople(bStart) 方法，该方法统计今天的住院人数。
//        如果统计成功，将返回包含统计结果的成功响应，消息内容为 "统计今天住院人数成功"。
//        如果统计失败，将返回失败响应。
//        这样，控制器可以处理查找空床位、增加床位信息、查询住院信息、查询所有床位信息、根据ID查找床位、增加床位信息、删除床位信息、清空床位信息和统计今天住院人数的请求，
//        并根据操作结果返回相应的响应消息
@RestController
@RequestMapping("bed")
public class BedController {
    @Autowired
    private BedService bedService;

    /**
     * 查找所有空床位
     */
    @RequestMapping("findNullBed")
    public ResponseData findNullBed(){
        return ResponseData.success("查找所有空床位成功", this.bedService.findNullBed());
    }

    /**
     * 增加床位信息
     */
    @RequestMapping("updateBed")
    public ResponseData updateBed(Bed bed) {
        if (this.bedService.updateBed(bed))
        return ResponseData.success("增加床位成功！");
        return ResponseData.fail("增加床位失败！");
    }
    /**
     * 根据pId查询住院
     */
    @RequestMapping("findBedByPid")
    public ResponseData findBedByPid(@RequestParam(value = "pId") int pId){
        return ResponseData.success("根据pId查询住院成功", this.bedService.findBedByPid(pId)) ;
    }
    /**
     * 分页模糊查询所有床位信息
     */
    @RequestMapping("findAllBeds")
    public ResponseData findAllBeds(int pageNumber, int size, String query){
        return ResponseData.success("返回所有床位信息成功", this.bedService.findAllBeds(pageNumber, size, query));
    }
    /**
     * 根据id查找床位
     */
    @RequestMapping("findBed")
    public ResponseData findBed(int bId){
        return ResponseData.success("根据id查找床位成功", this.bedService.findBed(bId));
    }
    /**
      * 增加床位信息
     */
    @RequestMapping("addBed")
    @ResponseBody
    public ResponseData addBed(Bed bed) {
        Boolean bo = this.bedService.addBed(bed);
        if (bo) {
            return ResponseData.success("增加床位信息成功");
        }
        return ResponseData.fail("增加床位信息失败！床号或已被占用");
    }
    /**
     * 删除药物信息
     */
    @RequestMapping("deleteBed")
    public ResponseData deleteBed(@RequestParam(value = "bId") int bId) {
        Boolean bo = this.bedService.deleteBed(bId);
        if (bo){
            return ResponseData.success("删除床位信息成功");
        }
        return ResponseData.fail("删除床位信息失败");
    }
    /**
     * 清空床位信息
     */
    @RequestMapping("emptyBed")
    public ResponseData emptyBed(int bId){
        if(this.bedService.emptyBed(bId))
            return ResponseData.success("清空床位信息成功");
        return ResponseData.fail("清空床位信息失败");
    }
    /**
     * 统计今天挂号人数
     */
    @RequestMapping("bedPeople")
    public ResponseData bedPeople(){
        String bStart = TodayUtil.getTodayYmd();
        return ResponseData.success("统计今天住院人数成功", this.bedService.bedPeople(bStart));
    }
}
