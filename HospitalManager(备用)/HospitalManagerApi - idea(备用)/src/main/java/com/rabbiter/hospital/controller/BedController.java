//这是一个床位管理系统的后台控制器类。
//
//        以下是这个控制器类的详细解释：
//
//        BedController - 这是一个使用@RestController注解的控制器类，用于处理与床位管理相关的请求。
//@Autowired - 使用@Autowired注解进行依赖注入，注入了BedService。
//@RequestMapping(“bed”) - 使用@RequestMapping注解，处理/bed的请求。
//        findNullBed() - 这是一个方法体，用于查找所有空床位。调用bedService的findNullBed方法，然后将结果封装在ResponseData对象里返回。
//        updateBed() - 这是一个方法体，接收Bed对象作为参数，调用bedService的updateBed方法进行增加床位信息。如果添加成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        findBedByPid() - 这是一个方法体，接收pId作为参数，调用bedService的findBedByPid方法根据pId查询住院信息。然后将结果封装在ResponseData对象里返回。
//        findAllBeds() - 这是一个方法体，接收pageNumber、size和query作为参数，调用bedService的findAllBeds方法进行分页模糊查询所有床位信息。然后将结果封装在ResponseData对象里返回。
//        findBed() - 这是一个方法体，接收bId作为参数，调用bedService的findBed方法根据bId查找床位。然后将结果封装在ResponseData对象里返回。
//        addBed() - 这是一个方法体，接收Bed对象作为参数，调用bedService的addBed方法进行增加床位信息。如果添加成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        deleteBed() - 这是一个方法体，接收bId作为参数，调用bedService的deleteBed方法进行删除床位信息。如果删除成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        emptyBed() - 这是一个方法体，接收bId作为参数，调用bedService的emptyBed方法进行清空床位信息。如果清空成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        bedPeople() - 这是一个方法体，用于统计今天住院人数。调用bedService的bedPeople方法，然后将结果封装在ResponseData对象里返回。

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
