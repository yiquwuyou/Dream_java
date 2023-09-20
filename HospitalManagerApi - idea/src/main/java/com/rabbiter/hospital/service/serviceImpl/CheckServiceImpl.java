package com.rabbiter.hospital.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.hospital.mapper.CheckMapper;
import com.rabbiter.hospital.pojo.Checks;
import com.rabbiter.hospital.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

//这段代码是检查信息服务的实现类 CheckServiceImpl，它处理了检查信息的增加、查询、更新和删除等操作。以下是该类的主要功能：
//
//        findAllChecks 方法：
//
//        分页模糊查询所有检查信息。
//        使用 MyBatis Plus 的 Page 类和 QueryWrapper 构建查询条件，支持分页查询和模糊查询。
//        返回查询结果和分页信息。
//        findCheck 方法：
//
//        根据检查的 chId 查找检查信息。
//        使用 MyBatis Plus 的 checkMapper.selectById(chId) 方法，根据检查的 chId 查找并返回检查信息。
//        addCheck 方法：
//
//        增加检查信息。
//        首先，检查检查信息是否已存在，如果已存在则返回 false。
//        使用 checkMapper.insert(checks) 方法，插入新的检查信息。
//        返回 true，表示增加检查信息成功。
//        deleteCheck 方法：
//
//        根据检查的 chId 删除检查信息。
//        使用 MyBatis Plus 的 checkMapper.deleteById(chId) 方法，根据检查的 chId 删除检查信息。
//        返回 true，表示删除检查信息成功。
//        modifyCheck 方法：
//
//        修改检查信息。
//        使用 MyBatis Plus 的 checkMapper.updateById(checks) 方法，更新检查信息。
//        返回 true，表示修改检查信息成功。
//        该实现类主要负责检查信息的管理和查询，包括检查信息的增加、查询、删除和修改等操作
@Service("CheckService")
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckMapper checkMapper;
    /**
     * 分页模糊查询所有检查信息
     */
    @Override
    public HashMap<String, Object> findAllChecks(int pageNumber, int size, String query) {
        Page<Checks> page = new Page<>(pageNumber, size);
        QueryWrapper<Checks> wrapper = new QueryWrapper<>();
        wrapper.like("ch_name", query);
        IPage<Checks> iPage = this.checkMapper.selectPage(page, wrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("size", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("checks", iPage.getRecords()); //查询到的记录
        return hashMap;
    }
    /**
     * 根据id查找检查
     */
    @Override
    public Checks findCheck(int chId){
        return this.checkMapper.selectById(chId);
    }
    /**
     * 增加检查信息
     */
    @Override
    public Boolean addCheck(Checks checks){
        //如果账号已存在则返回false
        List<Checks> checks1 = this.checkMapper.selectList(null);
        for (Checks checks2 : checks1) {
            if (checks.getChId() == checks2.getChId()) {
                return false;
            }
        }
        this.checkMapper.insert(checks);
        return true;
    }
    /**
     * 删除检查信息
     */
    @Override
    public Boolean deleteCheck(int chId) {
        this.checkMapper.deleteById(chId);
        return true;
    }
    /**
     * 修改检查信息
     */
    @Override
    public Boolean modifyCheck(Checks checks) {
        int i = this.checkMapper.updateById(checks);
        System.out.println("影响行数："+i);
        return true;
    }
}
