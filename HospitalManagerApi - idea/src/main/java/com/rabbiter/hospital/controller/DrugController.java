package com.rabbiter.hospital.controller;

import com.rabbiter.hospital.pojo.Drug;
import com.rabbiter.hospital.service.DrugService;
import com.rabbiter.hospital.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//这段代码是一个 Spring Boot 控制器，用于处理药物相关的请求。以下是它的主要功能：
//
//        findAllDrugs 方法：
//
//        接收分页参数 pageNumber、size 和查询关键字 query。
//        调用 drugService.findAllDrugs(pageNumber, size, query) 方法，返回分页模糊查询所有药物信息的结果。
//        findDrug 方法：
//
//        接收药物的 ID drId。
//        调用 drugService.findDrug(drId) 方法，根据 ID 查找药物信息。
//        reduceDrugNumber 方法：
//
//        接收药物的 ID drId 和使用数量 usedNumber。
//        调用 drugService.reduceDrugNumber(drId, usedNumber) 方法，减少药物数量。
//        addDrug 方法：
//
//        接收药物信息。
//        调用 drugService.addDrug(drug) 方法，增加药物信息。
//        deleteDrug 方法：
//
//        接收药物的 ID drId。
//        调用 drugService.deleteDrug(drId) 方法，删除药物信息。
//        modifyDrug 方法：
//
//        接收药物信息。
//        调用 drugService.modifyDrug(drug) 方法，修改药物信息。
//        这段代码实现了药物信息的增删改查功能，并支持分页模糊查询。根据不同的请求，它返回不同的响应数据，用于与前端进行交互
@RestController
@RequestMapping("drug")
public class DrugController {
    @Autowired
    private DrugService drugService;
    /**
     * 分页模糊查询所有药物信息
     */
    @RequestMapping("findAllDrugs")
    public ResponseData findAllDrugs(int pageNumber, int size, String query){
        return ResponseData.success("返回所有药物信息成功", this.drugService.findAllDrugs(pageNumber, size, query));
    }
    /**
     * 根据id查找药物
     */
    @RequestMapping("findDrug")
    public ResponseData findDrug(int drId){
        return ResponseData.success("根据id查找药物成功", this.drugService.findDrug(drId));
    }
    /**
     * 根据id删除药物数量
     */
    @RequestMapping("reduceDrugNumber")
    public ResponseData reduceDrugNumber(int drId,int usedNumber){
        if (this.drugService.reduceDrugNumber(drId, usedNumber))
            return ResponseData.success("根据id删除药物数量成功");
        return ResponseData.fail("根据id删除药物数量失败");
    }
    /**
     * 增加药物信息
     */
    @RequestMapping("addDrug")
    @ResponseBody
    public ResponseData addDrug(Drug drug) {
        Boolean bo = this.drugService.addDrug(drug);
        if (bo) {
            return ResponseData.success("增加药物信息成功");
        }
        return ResponseData.fail("增加药物信息失败！账号或已被占用");
    }
    /**
     * 删除药物信息
     */
    @RequestMapping("deleteDrug")
    public ResponseData deleteDrug(@RequestParam(value = "drId") int drId) {
        Boolean bo = this.drugService.deleteDrug(drId);
        if (bo){
            return ResponseData.success("删除药物信息成功");
        }
        return ResponseData.fail("删除药物信息失败");
    }
    /**
     * 修改药物信息
     */
    @RequestMapping("modifyDrug")
    @ResponseBody
    public ResponseData modifyDrug(Drug drug) {
        this.drugService.modifyDrug(drug);
        return ResponseData.success("修改药物信息成功");
    }
}
