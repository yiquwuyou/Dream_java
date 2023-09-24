//这是一个检查项目管理系统的后台控制器类。
//
//        以下是这个控制器类的详细解释：
//
//        CheckController - 这是一个使用@RestController注解的控制器类，用于处理与检查项目管理相关的请求。
//@Autowired - 使用@Autowired注解进行依赖注入，注入了CheckService。
//@RequestMapping(“check”) - 使用@RequestMapping注解，处理/check的请求。
//        findAllChecks() - 这是一个方法体，接收pageNumber、size和query作为参数，调用checkService的findAllChecks方法进行分页模糊查询所有检查信息。然后将结果封装在ResponseData对象里返回。
//        findCheck() - 这是一个方法体，接收chId作为参数，调用checkService的findCheck方法根据chId查找检查。然后将结果封装在ResponseData对象里返回。
//        addCheck() - 这是一个方法体，接收Checks对象作为参数，调用checkService的addCheck方法进行增加检查信息。如果添加成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        deleteCheck() - 这是一个方法体，接收chId作为参数，调用checkService的deleteCheck方法进行删除检查信息。如果删除成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        modifyCheck() - 这是一个方法体，接收Checks对象作为参数，调用checkService的modifyCheck方法进行修改检查信息。然后返回一个带有成功信息的ResponseData对象。

package com.rabbiter.hospital.controller;

import com.rabbiter.hospital.pojo.Checks;
import com.rabbiter.hospital.service.CheckService;
import com.rabbiter.hospital.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("check")
public class CheckController {
    @Autowired
    private CheckService checkService;
    /**
     * 分页模糊查询所有检查信息
     */
    @RequestMapping("findAllChecks")
    public ResponseData findAllChecks(int pageNumber, int size, String query){
        return ResponseData.success("返回所有检查信息成功", this.checkService.findAllChecks(pageNumber, size, query));
    }
    /**
     * 根据id查找检查
     */
    @RequestMapping("findCheck")
    public ResponseData findCheck(int chId){
        return ResponseData.success("根据id查找检查成功", this.checkService.findCheck(chId));
    }
    /**
     * 增加检查信息
     */
    @RequestMapping("addCheck")
    @ResponseBody
    public ResponseData addCheck(Checks checks) {
        Boolean bo = this.checkService.addCheck(checks);
        if (bo) {
            return ResponseData.success("增加检查信息成功");
        }
        return ResponseData.fail("增加检查信息失败！账号或已被占用");
    }
    /**
     * 删除药物信息
     */
    @RequestMapping("deleteCheck")
    public ResponseData deleteCheck(@RequestParam(value = "chId") int chId) {
        Boolean bo = this.checkService.deleteCheck(chId);
        if (bo){
            return ResponseData.success("删除检查信息成功");
        }
        return ResponseData.fail("删除检查信息失败");
    }
    /**
     * 修改检查信息
     */
    @RequestMapping("modifyCheck")
    @ResponseBody
    public ResponseData modifyCheck(Checks checks) {
        this.checkService.modifyCheck(checks);
        return ResponseData.success("修改检查项目信息成功");
    }

}
