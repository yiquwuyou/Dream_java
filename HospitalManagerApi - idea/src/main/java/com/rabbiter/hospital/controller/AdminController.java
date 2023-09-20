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

// 后台管理功能
@RestController
@RequestMapping("admin")
public class AdminController {

    // 自动注入依赖的服务类
    @Autowired
    private AdminService adminService;        // 管理员相关的业务逻辑，如管理员的登陆验证
    @Autowired
    private DoctorService doctorService;      // 医生相关的业务逻辑，包括医生的增删改查等操作
    @Autowired
    private PatientService patientService;    // 患者相关的业务逻辑，包括患者的增删改查等操作
    @Autowired
    private OrderService orderService;        // 挂号信息相关的业务逻辑，包括挂号信息的增删改查等操作

    /**
     * 登录数据验证
     */
    // 用于验证 管理员 登录，处理登录请求
    @PostMapping("/login")
    //  这个注解告诉 Spring MVC，方法返回的对象将被序列化为 JSON 或其他格式并写入响应体中，而不是跳转到一个视图
    @ResponseBody
    // @RequestParam 用于从HTTP请求中获取请求参数的值， 它可以应用在方法的参数上，用于指定获取哪个请求参数的值
    // @RequestParam("aId") 和 @RequestParam("aPassword") 分别用于获取 HTTP 请求中名为 "aId" 和 "aPassword" 的请求参数的值，
    // 并将它们分别赋值给 aId 和 aPassword 这两个方法参数
    public ResponseData login(@RequestParam("aId") int aId, @RequestParam("aPassword") String aPassword) {
        // 用于用户登陆验证  用 selectById 方法基于 aId 从数据库中检索 Admin对象
        // 如果没有找到Admin对象，则返回 null
        // 如果找到了 Admin 对象，会继续检查 提供的 password 和存储的 管理员密码 是否匹配
        // 如果匹配，则返回 Admin对象
        // 如果不匹配，则返回 null
        Admin admin = this.adminService.login(aId, aPassword);
        // 如果 admin 对象不为 null，表示用户登录验证通过。
        //
        //创建一个空的 HashMap 对象 map，用于存储需要放入 JWT 中的数据。
        //
        //将用户的姓名 aName 和账号 aId 存入 map 中。
        //
        //调用 JwtUtil.getToken(map) 方法生成 JWT，并将生成的 JWT 存入 map 中的键 "token" 中。
        //
        //使用 ResponseData.success 方法创建一个成功的响应，其中包含 "登录成功" 的消息和 map 对象作为响应数据。
        //
        //如果 admin 为 null，表示用户登录验证失败，那么使用 ResponseData.fail 方法创建一个失败的响应，包含 "登录失败，密码或账号错误" 的消息。
        //
        //最终，根据用户登录验证的结果，返回一个带有相应消息和数据的 JSON 响应给客户端。如果验证成功，客户端可以获取到生成的 JWT，用于后续的请求身份验证。
        // 如果验证失败，客户端会收到登录失败的消息。这是一种常见的用户认证流程
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
    // 上面的代码是一个控制器方法，用于处理医护人员信息的查询请求。这个方法使用了Spring的@RequestMapping注解，映射了一个路径，接受三个请求参数：pageNumber、size 和 query。
    //
    //具体步骤如下：
    //
    //当客户端发送请求到findAllDoctors路径时，Spring会调用这个方法来处理请求。
    //
    //方法中使用@RequestParam注解来获取请求中的pageNumber、size和query参数的值。
    //
    //然后，调用doctorService.findAllDoctors(pageNumber, size, query)方法来查询医护人员信息。这个方法可能会根据参数进行分页查询，并返回医护人员信息的结果。
    //
    //最后，将查询结果和一个成功消息封装到ResponseData对象中，并通过ResponseData.success方法返回给客户端。
    //
    //这个方法的目的是根据客户端提供的分页和查询条件，查询医护人员信息，并将查询结果以JSON格式返回给客户端。客户端可以使用这些信息来展示医护人员的列表或者进行其他操作
    @RequestMapping("findAllDoctors")
    public ResponseData findAllDoctors(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "size") int size, @RequestParam(value = "query") String query){
        return ResponseData.success("返回医护人员信息成功",  this.doctorService.findAllDoctors(pageNumber, size, query));
    }
    /**
     * 根据id查找医生
     */
//    上面的代码是一个控制器方法，用于处理查询医生信息的请求。这个方法使用了Spring的@RequestMapping注解，映射了一个路径，接受一个请求参数dId，该参数表示医生的唯一标识符。
//
//    具体步骤如下：
//
//    当客户端发送请求到findDoctor路径时，Spring会调用这个方法来处理请求。
//
//    方法中使用@RequestParam注解来获取请求中的dId参数的值。
//
//    然后，调用doctorService.findDoctor(dId)方法来查询医生信息。这个方法根据dId参数查找医生，并返回医生的详细信息。
//
//    最后，将查询结果和一个成功消息封装到ResponseData对象中，并通过ResponseData.success方法返回给客户端。
//
//    这个方法的目的是根据客户端提供的医生唯一标识符，查询医生的详细信息，并将查询结果以JSON格式返回给客户端。客户端可以使用这些信息来展示医生的详细信息或者进行其他操作
    @RequestMapping("findDoctor")
    public ResponseData findDoctor(@RequestParam(value = "dId") int dId) {
        return ResponseData.success("查询医生成功", this.doctorService.findDoctor(dId));
    }
    /**
     * 增加医生信息
     */
    // 调用了this.doctorService.addDoctor(doctor)来尝试添加医生信息。如果添加成功（返回true），则返回一个成功的ResponseData对象，其中包含了成功的消息："增加医生信息成功"
    // 如果添加失败（返回false），则返回一个失败的ResponseData对象，其中包含了失败的消息："增加医生信息失败！账号或已被占用"
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
    // 接收一个类型为 Doctor 的参数 doctor，这个参数包含了要修改的医生信息。
    //
    //调用 doctorService.modifyDoctor(doctor) 方法来执行修改医生信息的操作。
    //
    //不管修改是否成功，都会返回一个成功的响应，包含消息 "修改医生信息成功"
    @RequestMapping("modifyDoctor")
    @ResponseBody
    public ResponseData modifyDoctor(Doctor doctor) {
        this.doctorService.modifyDoctor(doctor);
        return ResponseData.success("修改医生信息成功");
    }
    /**
     * 分页模糊查询所有患者信息
     */
    // 接收三个参数：
    //
    //pageNumber：要查询的页数。
    //size：每页的大小（每页显示多少条记录）。
    //query：模糊查询的关键词。
    //调用 patientService.findAllPatients(pageNumber, size, query) 方法来执行查询患者信息的操作。这个方法会根据传入的页数、每页大小和关键词进行查询，并返回符合条件的患者信息列表。
    //
    //构建一个成功的响应，消息内容为 "返回患者信息成功"，并将查询到的患者信息列表作为响应数据返回
    @RequestMapping("findAllPatients")
    public ResponseData findAllPatients(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "size") int size, @RequestParam(value = "query") String query){
        return ResponseData.success("返回患者信息成功",  this.patientService.findAllPatients(pageNumber, size, query));
    }
    /**
     * 删除患者信息
     */
    // 接收一个参数 pId，表示要删除的患者的唯一标识。
    //
    //调用 patientService.deletePatient(pId) 方法来执行删除患者信息的操作。这个方法会尝试删除指定 pId 的患者信息。
    //
    //根据删除结果构建响应：
    //
    //如果删除成功（bo 为 true），则构建一个成功的响应，消息内容为 "删除患者信息成功"。
    //如果删除失败（bo 为 false），则构建一个失败的响应，消息内容为 "删除患者信息失败"
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
    // 接收三个参数：
    //
    //pageNumber：表示要查询的页码。
    //size：表示每页的大小，即每页包含多少条挂号信息。
    //query：表示查询条件，可能包含一些过滤条件，用于筛选挂号信息。
    //调用 orderService.findAllOrders(pageNumber, size, query) 方法来执行查询挂号信息的操作。这个方法会根据传入的页码、每页大小和查询条件从数据库中获取相应的挂号信息列表。
    //
    //根据查询结果构建响应：
    //
    //如果查询成功，将查询到的挂号信息列表放入响应的数据字段，并构建一个成功的响应，消息内容为 "返回挂号信息成功"。
    //如果查询失败或没有匹配的挂号信息，可以根据具体情况构建一个失败的响应，消息内容可以是 "没有匹配的挂号信息" 或其他适当的消息
    @RequestMapping("findAllOrders")
    public ResponseData findAllOrders(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "size") int size, @RequestParam(value = "query") String query){
        return ResponseData.success("返回挂号信息成功",  this.orderService.findAllOrders(pageNumber, size, query));
    }
    /**
     * 删除挂号信息
     */
    // 接收一个参数 oId，表示要删除的挂号信息的唯一标识。
    //
    //调用 orderService.deleteOrder(oId) 方法来执行删除挂号信息的操作。这个方法会根据传入的 oId 从数据库中删除相应的挂号信息。
    //
    //根据删除操作的结果构建响应：
    //
    //如果删除成功，将构建一个成功的响应，消息内容为 "删除挂号信息成功"。
    //如果删除失败，将构建一个失败的响应，消息内容为 "删除挂号信息失败"
    @RequestMapping("deleteOrder")
    public ResponseData deleteOrder(@RequestParam(value = "oId") int oId) {
        Boolean bo = this.orderService.deleteOrder(oId);
        if (bo){
            return ResponseData.success("删除挂号信息成功");
        }
        return ResponseData.fail("删除挂号信息失败");
    }

}
