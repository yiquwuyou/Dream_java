package com.rabbiter.hospital.service.serviceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.hospital.mapper.ArrangeMapper;
import com.rabbiter.hospital.mapper.DoctorMapper;
import com.rabbiter.hospital.pojo.Arrange;
import com.rabbiter.hospital.pojo.Doctor;
import com.rabbiter.hospital.service.DoctorService;
import com.rabbiter.hospital.utils.Md5Util;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

//这段代码是医生信息服务的实现类 DoctorServiceImpl，它处理了医生信息的增加、查询、更新、删除、导入和导出等操作。以下是该类的主要功能：
//
//        login 方法：
//
//        用于登录数据校验，验证医生的账号和密码。
//        对密码进行 MD5 加密后，与数据库中的医生密码进行比较，如果匹配则返回医生信息，否则返回 null。
//        findAllDoctors 方法：
//
//        分页模糊查询所有医护人员信息。
//        使用 MyBatis Plus 的 Page 类和 QueryWrapper 构建查询条件，支持分页查询和模糊查询。
//        返回查询结果和分页信息。
//        findDoctor 方法：
//
//        根据医生的 dId 查找医生信息。
//        使用 MyBatis Plus 的 doctorMapper.selectById(dId) 方法，根据医生的 dId 查找并返回医生信息。
//        addDoctor 方法：
//
//        增加医生信息。
//        首先，检查医生账号是否已存在，如果已存在则返回 false。
//        对密码进行 MD5 加密。
//        设置医生的初始状态、星级和挂号人数等信息。
//        使用 doctorMapper.insert(doctor) 方法，插入新的医生信息。
//        返回 true，表示增加医生信息成功。
//        deleteDoctor 方法：
//
//        根据医生的 dId 删除医生信息。
//        更新医生的状态为不可用。
//        返回 true，表示删除医生信息成功。
//        modifyDoctor 方法：
//
//        修改医生信息。
//        使用 MyBatis Plus 的 doctorMapper.updateById(doctor) 方法，更新医生信息。
//        返回 true，表示修改医生信息成功。
//        findDoctorBySection 方法：
//
//        根据科室查询所有医生信息。
//        返回查询结果。
//        findDoctorBySectionPage 方法：
//
//        分页根据科室查询所有医生信息。
//        查询结果包括医生的基本信息和是否已排班信息。
//        返回查询结果和分页信息。
//        updateStar 方法：
//
//        用于用户评价医生，更新医生的星级评价。
//        使用 MyBatis Plus 的 doctorMapper.updateStar(dId, dStar) 方法，更新医生的星级。
//        返回 true，表示更新星级成功。
//        uploadExcel 方法：
//
//        用于上传 Excel 文件并导入医生信息。
//        使用 ExcelImportUtil 导入 Excel 文件，将 Excel 数据转化为医生信息对象，然后插入数据库。
//        返回 true，表示导入医生信息成功。
//        downloadExcel 方法：
//
//        用于导出医生信息到 Excel 文件。
//        使用 ExcelExportUtil 导出医生信息到 Excel 工作簿，并设置响应头信息，使浏览器下载 Excel 文件。
//        返回 true，表示导出医生信息成功。
//        findAll 方法：
//
//        查询所有医生信息，不分页。
//        返回医生信息列表。
//        该实现类主要负责医生信息的管理和查询，包括医生信息的增加、查询、删除、修改、导入和导出等操作
@Service("DoctorService")
public class DoctorServiceImpl implements DoctorService {
    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private ArrangeMapper arrangeMapper;

    /**
     * 登录数据校验
     * */
    @Override
    public Doctor login(int dId, String dPassword){
        Doctor doctor = this.doctorMapper.selectById(dId);
        String password = Md5Util.getMD5(dPassword);
        if (doctor == null) {
            return null;
        } else {
            if ((doctor.getdPassword()).equals(password)) {
                return doctor;
            }
        }
        return null;
    }
    /**
     * 分页模糊查询所有医护人员信息
     */
    @Override
    public HashMap<String, Object> findAllDoctors(int pageNumber, int size, String query) {
        Page<Doctor> page = new Page<>(pageNumber, size);
        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();
        wrapper.like("d_name", query).orderByDesc("d_state");
        IPage<Doctor> iPage = this.doctorMapper.selectPage(page, wrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("pages", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("doctors", iPage.getRecords()); //查询到的记录
        return hashMap;
    }

    /**
     * 根据id查找医生
     */
    @Override
    public Doctor findDoctor(int dId) {
        return this.doctorMapper.selectById(dId);
    }

    /**
     * 增加医生信息
     */
    @Override
    public Boolean addDoctor(Doctor doctor) {
        //如果账号已存在则返回false
        List<Doctor> doctors = this.doctorMapper.selectList(null);
        for (Doctor doctor1 : doctors) {
            if (doctor.getdId() == doctor1.getdId()) {
                return false;
            }
        }
        //密码加密
        String password = Md5Util.getMD5(doctor.getdPassword());
        doctor.setdPassword(password);
        doctor.setdState(1);
        doctor.setdStar(0.00);
        doctor.setdPeople(0);
        this.doctorMapper.insert(doctor);
        return true;
    }

    /**
     * 删除医生信息
     */
    @Override
    public Boolean deleteDoctor(int dId) {
        Doctor doctor = new Doctor();
        doctor.setdId(dId);
        doctor.setdState(0);
        this.doctorMapper.updateById(doctor);
        return true;
    }

    /**
     * 修改医生信息
     */
    @Override
    public Boolean modifyDoctor(Doctor doctor) {
//        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("d_id", doctor.getDId());
//        this.doctorMapper.update(doctor, queryWrapper);
        int i = this.doctorMapper.updateById(doctor);
        System.out.println("影响行数："+i);
        return true;
    }
    /**
     * 根据科室查询所有医生信息
     */
    @Override
    public HashMap<String, Object> findDoctorBySection(String dSection){
//        HashMap<String, Object> hashMap = new HashMap<>();
//        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("d_section", dSection).eq("d_state", 1);
//        List<Doctor> doctors = this.doctorMapper.selectList(queryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("doctors", this.doctorMapper.findDoctorBySection(dSection));
        return map;

    }
    /**
     * 分页根据科室查询所有医生信息
     */
    @Override
    public HashMap<String, Object> findDoctorBySectionPage(int pageNumber, int size, String query, String arrangeDate, String dSection) {
        Page<Doctor> page = new Page<>(pageNumber, size);
        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();
        wrapper.select("d_id", "d_name", "d_gender", "d_post", "d_section").like("d_name", query).eq("d_section", dSection).orderByDesc("d_state");
        IPage<Doctor> iPage = this.doctorMapper.selectPage(page, wrapper);
        List<Doctor> records = iPage.getRecords();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("pages", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("doctors", records); //查询到的记录

        // 查询医生是否已排班
        for (Doctor doctor : records) {
            Arrange arrange = arrangeMapper.selectOne(
                    new QueryWrapper<Arrange>().eq("ar_time", arrangeDate).eq("d_id", doctor.getdId())
            );
            if(arrange != null) {
                doctor.setArrangeId(arrange.getArId());
            }

        }
        return hashMap;
    }

    /**
     * 用户评价
     */
    @Override
    public Boolean updateStar(int dId, Double dStar){

        if(this.doctorMapper.updateStar(dId, dStar))
            return true;
        return false;
    }
    /**
     * 上传Excel导入数据
     */
    @Override
    public Boolean uploadExcel(MultipartFile multipartFile) throws Exception {
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
       List<Doctor> doctors = ExcelImportUtil.importExcel(multipartFile.getInputStream(), Doctor.class, params);
        for (Doctor doctor: doctors){
            doctor.setdPassword(Md5Util.getMD5(doctor.getdPassword()));
            this.addDoctor(doctor);
        }
        return true;
    }
    /**
     * Excel导出数据
     */
    @Override
    public Boolean downloadExcel(HttpServletResponse response) throws IOException {
        List<Doctor> doctors = this.findAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Doctor.class, doctors);
        ServletOutputStream stream = response.getOutputStream();
        response.setHeader("content-disposition", "attachment;fileName="+ URLEncoder.encode("医院医生信息.xlsx", "UTF-8"));
        workbook.write(stream);
        stream.close();
        workbook.close();
        return true;
    }
    /**
     * 查询所有医生不分页
     */
    @Override
    public List<Doctor> findAll(){
        return this.doctorMapper.selectList(null);
    }
}
