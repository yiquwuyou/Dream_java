//这段代码是一个 Spring Boot 控制器（Controller），用于处理医院管理系统的后台管理功能。以下是主要功能：
//
//        登录验证：
//
//        login 方法用于验证管理员登录。它接受管理员的 ID (aId) 和密码 (aPassword) 作为输入，并调用 adminService.login(aId, aPassword) 进行登录验证。
//        如果登录成功，将生成一个 JWT 令牌，将管理员的姓名 (aName)、ID (aId) 和令牌 (token) 返回给客户端。
//        医护人员管理：
//
//        findAllDoctors 方法用于分页模糊查询所有医护人员信息。
//        findDoctor 方法根据医生的 ID 查询医生信息。
//        addDoctor 方法用于添加医生信息。
//        deleteDoctor 方法用于删除医生信息。
//        modifyDoctor 方法用于修改医生信息。
//        患者管理：
//
//        findAllPatients 方法用于分页模糊查询所有患者信息。
//        deletePatient 方法用于删除患者信息。
//        挂号信息管理：
//
//        findAllOrders 方法用于分页模糊查询所有挂号信息。
//        deleteOrder 方法用于删除挂号信息。
//        这些方法通过调用相应的服务类（AdminService、DoctorService、PatientService 和 OrderService）来执行具体的业务逻辑。此外，还使用了 JwtUtil 工具类来生成和验证 JWT 令牌，以实现用户认证和授权功能。
//
//        这个控制器提供了后台管理系统的核心功能，包括管理员登录、医护人员管理、患者管理和挂号信息管理。

package com.rabbiter.hospital.controller;

import com.rabbiter.hospital.pojo.Admin;
import com.rabbiter.hospital.pojo.Doctor;
import com.rabbiter.hospital.service.AdminService;
import com.rabbiter.hospital.service.DoctorService;
import com.rabbiter.hospital.service.OrderService;
import com.rabbiter.hospital.service.PatientService;
import com.rabbiter.hospital.utils.JwtUtil;
import com.rabbiter.hospital.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private OrderService orderService;

    /**
     * 登录数据验证
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseData login(@RequestParam("aId") int aId, @RequestParam("aPassword") String aPassword) {
        Admin admin = this.adminService.login(aId, aPassword);
        if (admin != null) {
            Map<String,String> map = new HashMap<>();
            map.put("aName", admin.getAName());
            map.put("aId", String.valueOf(admin.getAId()));
            String token = JwtUtil.getToken(map);
            map.put("token", token);
            return ResponseData.success("登录成功", map);
        } else {
            return ResponseData.fail("登录失败，密码或账号错误");
        }
    }

    /**
     * 分页模糊查询所有医护人员信息
     */
    @RequestMapping("findAllDoctors")
    public ResponseData findAllDoctors(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "size") int size, @RequestParam(value = "query") String query){
        return ResponseData.success("返回医护人员信息成功",  this.doctorService.findAllDoctors(pageNumber, size, query));
    }
    /**
     * 根据id查找医生
     */
    @RequestMapping("findDoctor")
    public ResponseData findDoctor(@RequestParam(value = "dId") int dId) {
        return ResponseData.success("查询医生成功", this.doctorService.findDoctor(dId));
    }
    /**
     * 增加医生信息
     */
    @RequestMapping("addDoctor")
    @ResponseBody
    public ResponseData addDoctor(Doctor doctor) {
        Boolean bo = this.doctorService.addDoctor(doctor);
        if (bo) {
            return ResponseData.success("增加医生信息成功");
        }
        return ResponseData.fail("增加医生信息失败！账号或已被占用");
    }
    /**
     * 删除医生信息
     */
    @RequestMapping("deleteDoctor")
    public ResponseData deleteDoctor(@RequestParam(value = "dId") int dId) {
        Boolean bo = this.doctorService.deleteDoctor(dId);
        if (bo){
            return ResponseData.success("删除医生信息成功");
        }
        return ResponseData.fail("删除医生信息失败");
    }
    /**
     * 修改医生信息
     * bug: dState会自动更新为0
     */
    @RequestMapping("modifyDoctor")
    @ResponseBody
    public ResponseData modifyDoctor(Doctor doctor) {
        this.doctorService.modifyDoctor(doctor);
        return ResponseData.success("修改医生信息成功");
    }
    /**
     * 分页模糊查询所有患者信息
     */
    @RequestMapping("findAllPatients")
    public ResponseData findAllPatients(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "size") int size, @RequestParam(value = "query") String query){
        return ResponseData.success("返回患者信息成功",  this.patientService.findAllPatients(pageNumber, size, query));
    }
    /**
     * 删除患者信息
     */
    @RequestMapping("deletePatient")
    public ResponseData deletePatient(@RequestParam(value = "pId") int pId) {
        Boolean bo = this.patientService.deletePatient(pId);
        if (bo){
            return ResponseData.success("删除患者信息成功");
        }
        return ResponseData.fail("删除患者信息失败");
    }
    /**
     * 分页模糊查询所有挂号信息
     */
    @RequestMapping("findAllOrders")
    public ResponseData findAllOrders(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "size") int size, @RequestParam(value = "query") String query){
        return ResponseData.success("返回挂号信息成功",  this.orderService.findAllOrders(pageNumber, size, query));
    }
    /**
     * 删除挂号信息
     */
    @RequestMapping("deleteOrder")
    public ResponseData deleteOrder(@RequestParam(value = "oId") int oId) {
        Boolean bo = this.orderService.deleteOrder(oId);
        if (bo){
            return ResponseData.success("删除挂号信息成功");
        }
        return ResponseData.fail("删除挂号信息失败");
    }

}
