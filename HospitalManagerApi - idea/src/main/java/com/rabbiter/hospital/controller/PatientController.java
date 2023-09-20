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

//这段代码是一个 Spring Boot 控制器，用于处理患者相关的请求。以下是它的主要功能：
//
//        login 方法：
//
//        接收患者的 ID pId 和密码 pPassword。
//        调用 patientService.login(pId, pPassword) 方法，验证登录信息。
//        如果验证成功，生成一个 JWT token，并将患者的相关信息（pName、pId、pCard）以及 token 返回给前端。
//        findDoctorBySection 方法：
//
//        接收医生科室信息 dSection。
//        调用 doctorService.findDoctorBySection(dSection) 方法，根据科室查询所有医生信息。
//        addOrder 方法：
//
//        接收一个 Orders 对象和 arId。
//        调用 orderService.addOrder(order, arId) 方法，增加挂号信息。
//        findOrderByPid 方法：
//
//        接收患者的 ID pId。
//        调用 orderService.findOrderByPid(pId) 方法，根据患者的 ID 查询挂号信息。
//        addPatient 方法：
//
//        接收一个 Patient 对象，用于增加患者信息。
//        调用 patientService.addPatient(patient) 方法，增加患者信息。
//        downloadPDF 方法：
//
//        接收挂号单的 ID oId。
//        调用 orderMapper.findOrderByOid(oId) 方法，根据挂号单的 ID 查询挂号信息。
//        调用 PdfUtil.ExportPdf(request, response, order) 方法，生成 PDF 文件并返回给客户端。
//        patientAge 方法：
//
//        统计患者男女人数。
//        这段代码实现了患者相关的功能，包括登录验证、查询医生信息、挂号、查看挂号记录、下载挂号单 PDF 等操作，并根据不同的请求返回不同的响应数据，用于与前端进行交互
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
