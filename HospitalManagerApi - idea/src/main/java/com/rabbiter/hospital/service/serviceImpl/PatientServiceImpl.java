package com.rabbiter.hospital.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.hospital.mapper.PatientMapper;
import com.rabbiter.hospital.pojo.Patient;
import com.rabbiter.hospital.service.PatientService;
import com.rabbiter.hospital.utils.Md5Util;
import com.rabbiter.hospital.utils.TodayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//这段代码是患者信息服务的实现类 PatientServiceImpl，它处理了患者信息的增加、查询、更新、删除等操作。以下是该类的主要功能：
//
//        login 方法：
//
//        登录数据校验，根据患者的 pId 和密码进行登录验证。
//        使用 MyBatis Plus 的 patientMapper.selectById(pId) 方法查询患者信息，然后对比密码。
//        返回登录成功的患者信息。
//        findAllPatients 方法：
//
//        分页模糊查询所有患者信息。
//        使用 MyBatis Plus 的 Page 类和 QueryWrapper 构建查询条件，支持分页查询和模糊查询。
//        返回查询结果和分页信息。
//        deletePatient 方法：
//
//        删除患者信息。
//        使用 MyBatis Plus 的 updateById 方法，将患者的状态 pState 设置为 0 表示删除。
//        返回 true 表示删除成功。
//        findPatientById 方法：
//
//        根据患者的 pId 查询患者信息。
//        使用 MyBatis Plus 的 selectOne 方法，根据 pId 查询患者信息。
//        addPatient 方法：
//
//        增加患者信息。
//        先检查患者账号是否已存在，如果存在则返回 false。
//        计算患者年龄，并将密码使用 MD5 加密。
//        设置患者状态为 1 表示正常。
//        使用 MyBatis Plus 的 insert 方法插入患者信息。
//        返回 true 表示增加患者信息成功，返回 false 表示账号已存在。
//        patientAge 方法：
//
//        统计不同年龄段的患者人数。
//        调用 patientAge 方法统计患者的年龄分布，将结果存入列表 ageList。
//        返回 ageList 包含不同年龄段的患者人数。
//        这个实现类主要负责患者信息的管理和查询，包括患者信息的增加、查询、删除等操作。同时，它还统计了不同年龄段的患者人数
@Service("PatientService")
public class PatientServiceImpl implements PatientService {
    protected static final Logger Log = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Resource
    private PatientMapper patientMapper;


    /**
     * 登录数据校验
     * */
    @Override
    public Patient login(int pId, String pPassword){
        Patient patient = this.patientMapper.selectById(pId);
        String password = Md5Util.getMD5(pPassword);
        if (patient == null) {
            return null;
        } else {
            if ((patient.getPPassword()).equals(password)) {
                return patient;
            }
        }
        return null;
    }
    /**
     * 分页模糊查询所有患者信息
     */
    @Override
    public HashMap<String, Object> findAllPatients(int pageNumber, int size, String query) {
        Page<Patient> page = new Page<>(pageNumber, size);
        QueryWrapper<Patient> wrapper = new QueryWrapper<>();
        wrapper.like("p_name", query).orderByDesc("p_state");
        IPage<Patient> iPage = this.patientMapper.selectPage(page, wrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("pages", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("patients", iPage.getRecords()); //查询到的记录
        return hashMap;
    }

    /**
     * 删除患者信息
     */
    @Override
    public Boolean deletePatient(int pId) {
        Patient patient = new Patient();
        patient.setPId(pId);
        patient.setPState(0);
        this.patientMapper.updateById(patient);
        return true;
    }
    /**
     * 根据患者id查询患者信息
     */
    @Override
    public Patient findPatientById(int pId){
        QueryWrapper<Patient> wrapper = new QueryWrapper<>();
        wrapper.eq("p_id", pId);
        return this.patientMapper.selectOne(wrapper);
    }

    /**
     * 增加患者信息
     */
    @Override
    public Boolean addPatient(Patient patient) {
        //如果账号已存在则返回false
        List<Patient> patients = this.patientMapper.selectList(null);
        for (Patient patient1 : patients) {
            if (patient.getPId() == patient1.getPId()) {
                return false;
            }
            if ((patient.getPEmail()).equals(patient1.getPEmail()) ){
                return false;
            }
        }
        int yourYear = Integer.parseInt(patient.getPBirthday().substring(0, 4));
        int todayYear = Integer.parseInt(TodayUtil.getTodayYmd().substring(0,4));
        //密码md5加密
        String password = Md5Util.getMD5(patient.getPPassword());
        patient.setPPassword(password);
        patient.setPAge(todayYear-yourYear);
        patient.setPState(1);
        this.patientMapper.insert(patient);
        return true;
    }
    /**
     * 统计患者男女人数
     */
    public List<Integer> patientAge(){
        List<Integer> ageList = new ArrayList<>();
        Integer age1 = this.patientMapper.patientAge(0, 9);
        Integer age2 = this.patientMapper.patientAge(10, 19);
        Integer age3 = this.patientMapper.patientAge(20, 29);
        Integer age4 = this.patientMapper.patientAge(30, 39);
        Integer age5 = this.patientMapper.patientAge(40, 49);
        Integer age6 = this.patientMapper.patientAge(50, 59);
        Integer age7 = this.patientMapper.patientAge(60, 69);
        Integer age8 = this.patientMapper.patientAge(70, 79);
        Integer age9 = this.patientMapper.patientAge(80, 89);
        Integer age10 = this.patientMapper.patientAge(90, 99);
        ageList.add(age1);
        ageList.add(age2);
        ageList.add(age3);
        ageList.add(age4);
        ageList.add(age5);
        ageList.add(age6);
        ageList.add(age7);
        ageList.add(age8);
        ageList.add(age9);
        ageList.add(age10);
        return ageList;

    }


    }


