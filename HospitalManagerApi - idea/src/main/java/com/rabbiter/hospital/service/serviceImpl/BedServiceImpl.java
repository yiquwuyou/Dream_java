package com.rabbiter.hospital.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.hospital.mapper.BedMapper;
import com.rabbiter.hospital.pojo.Bed;
import com.rabbiter.hospital.service.BedService;
import com.rabbiter.hospital.utils.TodayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

//这段代码是床位信息服务的实现类 BedServiceImpl，它处理了床位信息的增加、查询、更新和删除等操作。以下是该类的主要功能：
//
//        findNullBed 方法：
//
//        查询所有空床位。
//        使用 MyBatis Plus 的 QueryWrapper 构建查询条件，选择 b_id，查询条件为 b_state 等于 0（表示床位为空）。
//        调用 bedMapper.selectList(wrapper) 方法，返回所有空床位的列表。
//        updateBed 方法：
//
//        更新床位信息，将床位标记为已占用。
//        首先，检查床位是否已被占用，如果已被占用则返回 false。
//        设置床位的状态为已占用（1），并设置占用的开始日期为今天的日期。
//        使用 bedMapper.updateById(bed) 方法，更新床位信息。
//        返回 true，表示更新床位信息成功。
//        findBedByPid 方法：
//
//        根据患者的 pId 查询占用的床位信息。
//        使用 MyBatis Plus 的 QueryWrapper 构建查询条件，查询条件为 p_id 等于给定的 pId。
//        调用 bedMapper.selectList(wrapper) 方法，返回符合条件的床位列表。
//        findAllBeds 方法：
//
//        分页模糊查询所有床位信息。
//        使用 MyBatis Plus 的 Page 类和 QueryWrapper 构建查询条件，支持分页查询和模糊查询。
//        返回查询结果和分页信息。
//        findBed 方法：
//
//        根据床位的 bId 查找床位信息。
//        使用 MyBatis Plus 的 bedMapper.selectById(bId) 方法，根据床位的 bId 查找并返回床位信息。
//        addBed 方法：
//
//        增加床位信息。
//        首先，检查床位是否已存在，如果已存在则返回 false。
//        设置床位的状态为未占用（0）。
//        使用 bedMapper.insert(bed) 方法，插入新的床位信息。
//        返回 true，表示增加床位信息成功。
//        deleteBed 方法：
//
//        根据床位的 bId 删除床位信息。
//        使用 MyBatis Plus 的 bedMapper.deleteById(bId) 方法，根据床位的 bId 删除床位信息。
//        返回 true，表示删除床位信息成功。
//        emptyBed 方法：
//
//        清空床位信息。
//        使用 MyBatis Plus 的 UpdateWrapper 构建更新条件，将床位的相关字段重置。
//        使用 bedMapper.update(null, wrapper) 方法，清空床位信息。
//        返回 true，表示清空床位信息成功。
//        bedPeople 方法：
//
//        统计今天挂号的人数。
//        根据指定的开始日期 bStart 统计今天挂号的人数。
//        使用 bedMapper.bedPeople(bStart) 方法，返回今天挂号的人数。
//        该实现类主要负责床位信息的管理和查询，包括床位状态的更新、查询空床位、分页查询床位信息、清空床位信息等操作
@Service("BedService")
public class BedServiceImpl implements BedService {

    @Resource
    private BedMapper bedMapper;

    /**
     * 查找所有空床位
     */
    @Override
    public List<Bed> findNullBed(){
        QueryWrapper<Bed> wrapper = new QueryWrapper<>();
        wrapper.select("b_id").eq("b_state", 0);
        return this.bedMapper.selectList(wrapper);
    }

    /**
     * 增加床位信息
     */
    @Override
    /**
     * 更新床位信息
     */
    public Boolean updateBed(Bed bed){
        Bed bed1 = this.bedMapper.selectById(bed.getBId());
        if (bed1.getBState() == 1)
            return false;
        bed.setBStart(TodayUtil.getTodayYmd());
        bed.setBState(1);
        bed.setVersion(bed1.getVersion());

        this.bedMapper.updateById(bed);
        return true;
    }
    /**
     * 根据pId查询挂号
     */
    public List<Bed> findBedByPid(int pId){
        QueryWrapper<Bed> wrapper = new QueryWrapper<>();
        wrapper.eq("p_id", pId);
        return this.bedMapper.selectList(wrapper);
    }
    /**
     * 分页模糊查询所有检查信息
     */
    @Override
    public HashMap<String, Object> findAllBeds(int pageNumber, int size, String query) {
        Page<Bed> page = new Page<>(pageNumber, size);
        QueryWrapper<Bed> wrapper = new QueryWrapper<>();
        wrapper.like("p_id", query);
        IPage<Bed> iPage = this.bedMapper.selectPage(page, wrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("size", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("beds", iPage.getRecords()); //查询到的记录
        return hashMap;
    }
    /**
     * 根据id查找检查
     */
    @Override
    public Bed findBed(int bId){
        return this.bedMapper.selectById(bId);
    }
    /**
     * 增加床位信息
     */
    @Override
    public Boolean addBed(Bed bed){
        //如果账号已存在则返回false
        List<Bed> beds = this.bedMapper.selectList(null);
        for (Bed bed1 : beds) {
            if (bed1.getBId() == bed.getBId()) {
                return false;
            }
        }
        bed.setBState(0);
        this.bedMapper.insert(bed);
        return true;
    }
    /**
     * 删除床位信息
     */
    @Override
    public Boolean deleteBed(int bId) {
        this.bedMapper.deleteById(bId);
        return true;
    }
    /**
     * 清空床位信息
     */
    public Boolean emptyBed(int bId){
        UpdateWrapper<Bed> wrapper = new UpdateWrapper<>();
        wrapper.set("p_id", -1).set("d_id", -1).set("b_reason", null).set("b_start", null).set("b_state", 0).eq("b_id", bId);
        this.bedMapper.update(null, wrapper);
        return true;

    }
    /**
     * 统计今天挂号人数
     */
    @Override
    public int bedPeople(String bStart){
        return this.bedMapper.bedPeople(bStart);
    }

}
