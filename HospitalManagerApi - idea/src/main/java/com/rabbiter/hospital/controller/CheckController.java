package com.rabbiter.hospital.controller;

import com.rabbiter.hospital.pojo.Checks;
import com.rabbiter.hospital.service.CheckService;
import com.rabbiter.hospital.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//这段代码是一个 Spring Boot 控制器，用于处理检查信息的相关请求。以下是它的主要功能：
//
//        findAllChecks 方法：
//
//        接收三个参数 pageNumber、size 和 query，用于分页模糊查询所有检查信息。
//        调用 checkService.findAllChecks(pageNumber, size, query) 方法，该方法根据分页和查询条件查询检查信息。
//        如果查询成功，将返回包含查询结果的成功响应，消息内容为 "返回所有检查信息成功"。
//        如果查询失败，将返回失败响应。
//        findCheck 方法：
//
//        接收一个参数 chId，表示要根据检查ID查找检查信息。
//        调用 checkService.findCheck(chId) 方法，该方法根据检查ID查找检查信息。
//        如果查询成功，将返回包含查询结果的成功响应，消息内容为 "根据id查找检查成功"。
//        如果查询失败，将返回失败响应。
//        addCheck 方法：
//
//        接收一个参数 checks，表示要添加的检查信息。
//        调用 checkService.addCheck(checks) 方法，该方法尝试添加检查信息。
//        如果添加成功，将返回成功响应，消息内容为 "增加检查信息成功"。
//        如果添加失败，将返回失败响应，消息内容为 "增加检查信息失败！账号或已被占用"。
//        deleteCheck 方法：
//
//        接收一个参数 chId，表示要删除的检查信息的唯一标识。
//        调用 checkService.deleteCheck(chId) 方法，该方法尝试删除检查信息。
//        如果删除成功，将返回成功响应，消息内容为 "删除检查信息成功"。
//        如果删除失败，将返回失败响应，消息内容为 "删除检查信息失败"。
//        modifyCheck 方法：
//
//        接收一个参数 checks，表示要修改的检查信息。
//        调用 checkService.modifyCheck(checks) 方法，该方法尝试修改检查信息。
//        如果修改成功，将返回成功响应，消息内容为 "修改检查项目信息成功"。
//        这样，控制器可以处理查询所有检查信息、根据ID查找检查、添加检查信息、删除检查信息和修改检查信息的请求，并根据操作结果返回相应的响应消息
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
