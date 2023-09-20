package com.rabbiter.hospital.controller;

import com.rabbiter.hospital.pojo.Doctor;
import com.rabbiter.hospital.service.DoctorService;
import com.rabbiter.hospital.service.OrderService;
import com.rabbiter.hospital.service.PatientService;
import com.rabbiter.hospital.utils.JwtUtil;
import com.rabbiter.hospital.utils.ResponseData;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//这段代码是一个 Spring Boot 控制器，用于处理医生相关的请求。以下是它的主要功能：
//
//        login 方法：
//
//        接收医生的账号 dId 和密码 dPassword。
//        调用 doctorService.login(dId, dPassword) 方法进行登录验证，如果验证成功，返回医生的信息以及生成的 JWT 令牌。
//        如果验证失败，返回登录失败的响应。
//        findOrderByNull 方法：
//
//        接收医生的账号 dId 和日期 oStart。
//        调用 orderService.findOrderByNull(dId, oStart) 方法，返回医生当天的挂号信息。
//        findPatientById 方法：
//
//        接收患者的账号 pId。
//        调用 patientService.findPatientById(pId) 方法，返回患者的信息。
//        findDoctorBySectionPage 方法：
//
//        接收医生科室信息，分页参数，和查询关键字。
//        调用 doctorService.findDoctorBySectionPage(pageNumber, size, query, arrangeDate, dSection) 方法，分页根据科室查询医生信息。
//        updateStar 方法：
//
//        接收医生的账号 dId 和评分 dStar。
//        调用 doctorService.updateStar(dId, dStar) 方法，更新医生的评分信息。
//        uploadExcel 方法：
//
//        接收上传的 Excel 文件 multipartFile。
//        调用 doctorService.uploadExcel(multipartFile) 方法，将上传的 Excel 数据导入系统中。
//        downloadExcel 方法：
//
//        接收 HTTP 响应对象 response。
//        调用 doctorService.downloadExcel(response) 方法，导出医生数据到 Excel 文件并返回给客户端。
//        这段代码实现了医生登录、查看当天挂号列表、查询患者信息、查找医生、评价医生、上传和导出 Excel 数据的功能。根据不同的请求，它返回不同的响应数据，用于与前端进行交互
@RestController
@RequestMapping("doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PatientService patientService;
    /**
     * 登录数据验证
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData login(@RequestParam(value = "dId") int dId, @RequestParam(value = "dPassword") String dPassword) {
        Doctor doctor = this.doctorService.login(dId, dPassword);
        if (doctor != null) {
            Map<String,String> map = new HashMap<>();
            map.put("dName", doctor.getdName());
            map.put("dId", String.valueOf(doctor.getdId()));
            String token = JwtUtil.getToken(map);
            map.put("token", token);
            //response.setHeader("token", token);
            return ResponseData.success("登录成功", map);
        } else {
            return ResponseData.fail("登录失败，密码或账号错误");
        }
    }
    /**
     * 查看当天挂号列表
     */
    @RequestMapping("findOrderByNull")
    public ResponseData findOrderByNull(@Param(value = "dId") int dId, @RequestParam(value = "oStart") String oStart){
        System.out.println("账号时间为"+dId+oStart);
        return ResponseData.success("返回当天挂号信息成功", this.orderService.findOrderByNull(dId,oStart));

    }
    /**
     * 根据患者id查询患者信息
     */
    @RequestMapping("findPatientById")
    public ResponseData findPatientById(int pId){
        return ResponseData.success("返回患者信息成功！", this.patientService.findPatientById(pId));
    }
    /**
     * 分页根据科室查询所有医生信息
     */
    @RequestMapping("findDoctorBySectionPage")
    public ResponseData findDoctorBySectionPage(int pageNumber, int size, String query, String arrangeDate, String dSection){
        return ResponseData.success("分页根据科室查询所有医生信息成功", this.doctorService.findDoctorBySectionPage(pageNumber, size, query, arrangeDate, dSection));
    }
    /**
     * 用户评价
     */
    @RequestMapping("updateStar")
    public ResponseData updateStar(int dId, Double dStar){
        if(this.doctorService.updateStar(dId, dStar))
            return ResponseData.success("评价成功");
        return ResponseData.fail("评价失败");
    }
    /**
     * 上传Excel导入数据
     */
    @RequestMapping(value = "uploadExcel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData uploadExcel(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        if (this.doctorService.uploadExcel(multipartFile))
        return ResponseData.success("上传Excel导入数据成功");
        return ResponseData.fail("上传Excel导入数据失败");

    }
    /**
     * Excel导出数据
     */
    @RequestMapping("downloadExcel")
    public ResponseData downloadExcel(HttpServletResponse response) throws IOException {
        if (this.doctorService.downloadExcel(response))
        return ResponseData.success("Excel导出数据成功");
        return ResponseData.fail("Excel导出数据失败");
    }
}
