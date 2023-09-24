//这是一个药物控制器类。
//
//        以下是这个控制器类的详细解释：
//
//        DrugController - 这是一个使用@RestController注解的控制器类，用于处理与药物相关的请求。
//@Autowired - 使用@Autowired注解进行依赖注入，注入了DrugService。
//@RequestMapping(“drug”) - 使用@RequestMapping注解，处理/drug的请求。
//        findAllDrugs() - 这是一个方法体，接收pageNumber、size和query作为参数，调用drugService的findAllDrugs方法进行分页模糊查询所有药物信息。然后返回一个带有成功信息和查询结果的ResponseData对象。
//        findDrug() - 这是一个方法体，接收drId作为参数，调用drugService的findDrug方法根据药物ID查找药物信息。然后返回一个带有成功信息和药物信息的ResponseData对象。
//        reduceDrugNumber() - 这是一个方法体，接收drId和usedNumber作为参数，调用drugService的reduceDrugNumber方法根据药物ID和使用数量来减少药物数量。如果删除成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        addDrug() - 这是一个方法体，接收Drug对象作为参数，调用drugService的addDrug方法添加药物信息。如果添加成功，返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        deleteDrug() - 这是一个方法体，接收drId作为参数，调用drugService的deleteDrug方法根据药物ID删除药物信息。如果删除成功，返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        modifyDrug() - 这是一个方法体，接收Drug对象作为参数，调用drugService的modifyDrug方法修改药物信息。然后返回一个带有成功信息的ResponseData对象。

package com.rabbiter.hospital.controller;

import com.rabbiter.hospital.pojo.Drug;
import com.rabbiter.hospital.service.DrugService;
import com.rabbiter.hospital.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
