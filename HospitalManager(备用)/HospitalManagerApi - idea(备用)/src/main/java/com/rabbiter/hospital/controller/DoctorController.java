//这是一个医生控制器类。
//
//        以下是这个控制器类的详细解释：
//
//        DoctorController - 这是一个使用@RestController注解的控制器类，用于处理与医生相关的请求。
//@Autowired - 使用@Autowired注解进行依赖注入，分别注入了DoctorService、OrderService和PatientService。
//@RequestMapping(“doctor”) - 使用@RequestMapping注解，处理/doctor的请求。
//        login() - 这是一个方法体，接收dId和dPassword作为参数，调用doctorService的login方法进行医生登录验证。如果登录成功，获取医生信息并生成token，将医生姓名、医生ID和token封装在Map中返回。如果登录失败，返回一个带有失败信息的ResponseData对象。
//        findOrderByNull() - 这是一个方法体，接收dId和oStart作为参数，调用orderService的findOrderByNull方法根据医生ID和起始时间查询当天的挂号列表。然后返回一个带有成功信息和查询结果的ResponseData对象。
//        findPatientById() - 这是一个方法体，接收pId作为参数，调用patientService的findPatientById方法根据患者ID查询患者信息。然后返回一个带有成功信息和患者信息的ResponseData对象。
//        findDoctorBySectionPage() - 这是一个方法体，接收pageNumber、size、query、arrangeDate和dSection作为参数，调用doctorService的findDoctorBySectionPage方法进行分页根据科室查询所有医生信息。然后返回一个带有成功信息和查询结果的ResponseData对象。
//        updateStar() - 这是一个方法体，接收dId和dStar作为参数，调用doctorService的updateStar方法进行医生评价更新。如果更新成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        uploadExcel() - 这是一个方法体，接收multipartFile作为参数，调用doctorService的uploadExcel方法进行Excel导入数据。如果导入成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        downloadExcel() - 这是一个方法体，接收response作为参数，调用doctorService的downloadExcel方法进行Excel导出数据。如果导出成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。

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
