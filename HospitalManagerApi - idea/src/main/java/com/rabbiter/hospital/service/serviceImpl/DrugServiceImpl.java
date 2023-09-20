package com.rabbiter.hospital.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.hospital.mapper.DrugMapper;
import com.rabbiter.hospital.pojo.Drug;
import com.rabbiter.hospital.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

//这段代码是药物信息服务的实现类 DrugServiceImpl，它处理了药物信息的增加、查询、更新、删除等操作。以下是该类的主要功能：
//
//        findAllDrugs 方法：
//
//        分页模糊查询所有药物信息。
//        使用 MyBatis Plus 的 Page 类和 QueryWrapper 构建查询条件，支持分页查询和模糊查询。
//        返回查询结果和分页信息。
//        findDrug 方法：
//
//        根据药物的 drId 查找药物信息。
//        使用 MyBatis Plus 的 drugMapper.selectById(drId) 方法，根据药物的 drId 查找并返回药物信息。
//        reduceDrugNumber 方法：
//
//        根据药物的 drId 和使用数量 usedNumber 减少药物数量。
//        先根据 drId 查询药物信息，然后检查药物的库存数量是否足够。
//        如果库存数量足够，就减少库存数量并更新数据库，返回 true，表示成功减少库存数量；否则返回 false，表示库存不足。
//        addDrug 方法：
//
//        增加药物信息。
//        首先，检查药物编号是否已存在，如果已存在则返回 false。
//        使用 drugMapper.insert(drug) 方法，插入新的药物信息。
//        返回 true，表示增加药物信息成功。
//        deleteDrug 方法：
//
//        根据药物的 drId 删除药物信息。
//        使用 MyBatis Plus 的 drugMapper.deleteById(drId) 方法，根据药物的 drId 删除药物信息。
//        返回 true，表示删除药物信息成功。
//        modifyDrug 方法：
//
//        修改药物信息。
//        使用 MyBatis Plus 的 drugMapper.updateById(drug) 方法，更新药物信息。
//        返回 true，表示修改药物信息成功。
//        该实现类主要负责药物信息的管理和查询，包括药物信息的增加、查询、删除、修改等操作
@Service("DrugService")
public class DrugServiceImpl implements DrugService {
    @Autowired
    private DrugMapper drugMapper;
    /**
     * 分页模糊查询所有药物信息
     */
    @Override
    public HashMap<String, Object> findAllDrugs(int pageNumber, int size, String query){
        Page<Drug> page = new Page<>(pageNumber, size);
        QueryWrapper<Drug> wrapper = new QueryWrapper<>();
        wrapper.like("dr_name", query);
        IPage<Drug> iPage = this.drugMapper.selectPage(page, wrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("size", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("drugs", iPage.getRecords()); //查询到的记录
        return hashMap;
    }

    /**
     * 根据id查找药物
     */
    @Override
    public Drug findDrug(int drId){
        return this.drugMapper.selectById(drId);
    }
    /**
     * 根据id删除药物数量
     */
    @Override
    public Boolean reduceDrugNumber(int drId,int usedNumber){
        Drug drug = this.drugMapper.selectById(drId);
        if(drug.getDrNumber() < usedNumber)
            return false;
        drug.setDrNumber(drug.getDrNumber()-usedNumber);
        this.drugMapper.updateById(drug);
        return true;
    }
    /**
     * 增加药物信息
     */
    public Boolean addDrug(Drug drug){
        //如果账号已存在则返回false
        List<Drug> drugs = this.drugMapper.selectList(null);
        for (Drug drug1 : drugs) {
            if (drug.getDrId() == drug1.getDrId()) {
                return false;
            }
        }
        this.drugMapper.insert(drug);
        return true;
    }
    /**
     * 删除药物信息
     */
    @Override
    public Boolean deleteDrug(int drId) {
        this.drugMapper.deleteById(drId);
        return true;
    }
    /**
     * 修改药物信息
     */
    @Override
    public Boolean modifyDrug(Drug drug) {
        int i = this.drugMapper.updateById(drug);
        System.out.println("影响行数："+i);
        return true;
    }
}
