//这是一个患者控制器类。
//
//        以下是这个控制器类的详细解释：
//
//        PatientController - 这是一个使用@RestController注解的控制器类，用于处理与患者相关的请求。
//@Autowired - 使用@Autowired注解进行依赖注入，注入了DoctorService、PatientService、OrderService、JedisPool和OrderMapper。
//@RequestMapping(“patient”) - 使用@RequestMapping注解，处理/patient的请求。
//        login() - 这是一个方法体，接收pId和pPassword作为参数，调用patientService的login方法验证登录信息。如果验证成功，则返回一个带有成功信息和用户信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        findDoctorBySection() - 这是一个方法体，接收dSection作为参数，调用doctorService的findDoctorBySection方法根据科室查询医生信息。然后返回一个带有成功信息和查询结果的ResponseData对象。
//        addOrder() - 这是一个方法体，接收order和arId作为参数，调用orderService的addOrder方法增加挂号信息。如果增加成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        findOrderByPid() - 这是一个方法体，接收pId作为参数，调用orderService的findOrderByPid方法根据患者ID查询挂号信息。然后返回一个带有成功信息和查询结果的ResponseData对象。
//        addPatient() - 这是一个方法体，接收patient作为参数，调用patientService的addPatient方法增加患者信息。如果增加成功，则返回一个带有成功信息的ResponseData对象；否则返回一个带有失败信息的ResponseData对象。
//        downloadPDF() - 这是一个方法体，接收request、response和oId作为参数，调用orderMapper的findOrderByOid方法根据挂号ID查询挂号信息。然后调用PdfUtil的ExportPdf方法生成并下载PDF文件。
//        patientAge() - 这是一个方法体，调用patientService的patientAge方法统计患者男女人数。然后返回一个带有成功信息和统计结果的ResponseData对象。

package com.rabbiter.hospital.controller;

import com.rabbiter.hospital.mapper.OrderMapper;
import com.rabbiter.hospital.pojo.Orders;
import com.rabbiter.hospital.pojo.Patient;
import com.rabbiter.hospital.service.DoctorService;
import com.rabbiter.hospital.service.OrderService;
import com.rabbiter.hospital.service.PatientService;
import com.rabbiter.hospital.utils.JwtUtil;
import com.rabbiter.hospital.utils.PdfUtil;
import com.rabbiter.hospital.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("patient")
public class PatientController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private JedisPool jedisPool;
    @Resource
    private OrderMapper orderMapper;

    /**
     * 登录数据验证
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData login(@RequestParam(value = "pId") int pId, @RequestParam(value = "pPassword") String pPassword) {
        Patient patient = this.patientService.login(pId, pPassword);
        if (patient != null) {
            Map<String,String> map = new HashMap<>();
            map.put("pName", patient.getPName());
            map.put("pId", String.valueOf(patient.getPId()));
            map.put("pCard", patient.getPCard());
            String token = JwtUtil.getToken(map);
            map.put("token", token);
            //response.setHeader("token", token);
            return ResponseData.success("登录成功", map);
        } else {
            return ResponseData.fail("登录失败，密码或账号错误");
        }
    }
    /**
     * 根据科室查询所有医生信息
     */
    @RequestMapping("findDoctorBySection")
    public ResponseData findDoctorBySection(@RequestParam(value = "dSection") String dSection){
        return ResponseData.success("根据科室查询所有医生信息成功", this.doctorService.findDoctorBySection(dSection));
    }
    /**
     * 增加挂号信息
     */
    @RequestMapping("addOrder")
    @ResponseBody
    public ResponseData addOrder(Orders order, String arId){
        System.out.println(arId);
        if (this.orderService.addOrder(order, arId))
        return ResponseData.success("插入挂号信息成功");
        return ResponseData.fail("插入挂号信息失败");
    }
    /**
     * 根据pId查询挂号
     */
    @RequestMapping("findOrderByPid")
    public ResponseData findOrderByPid(@RequestParam(value = "pId") int pId){
        return ResponseData.success("返回挂号信息成功", this.orderService.findOrderByPid(pId)) ;
    }

    /**
     * 增加患者信息
     */
    @RequestMapping("addPatient")
    @ResponseBody
    public ResponseData addPatient(Patient patient) {
        Boolean bo = this.patientService.addPatient(patient);
        if (bo) {
            return ResponseData.success("注册成功");
        }
        return ResponseData.fail("注册失败！账号或邮箱已被占用");
    }
    @GetMapping("/pdf")
    public void downloadPDF(HttpServletRequest request, HttpServletResponse response, int oId) throws Exception {
        Orders order = this.orderMapper.findOrderByOid(oId);
        PdfUtil.ExportPdf(request, response, order);
    }
    /**
     * 统计患者男女人数
     */
    @RequestMapping("patientAge")
    public ResponseData patientAge(){
        return  ResponseData.success("统计患者男女人数成功", this.patientService.patientAge());

    }
}
