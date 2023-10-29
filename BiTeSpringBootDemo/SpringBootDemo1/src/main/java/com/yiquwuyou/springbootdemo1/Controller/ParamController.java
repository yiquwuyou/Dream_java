package com.yiquwuyou.springbootdemo1.Controller;

import com.yiquwuyou.springbootdemo1.Person;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user1")
public class ParamController {

    @RequestMapping("/m1")
    public String m1(String name){
        return "接收到的参数name:" + name;
    }

    @RequestMapping("/m2")
    public String m2(String name, Integer age){
        return "接收到的参数name:" + name + " ;age:" + age;
    }

    @RequestMapping("/m3")
    public String m3(Person person){
        return "接收到的参数person:" + person.toString();
    }

    @RequestMapping("/m4")
    public String m4(@RequestParam(value = "name", required = false) String username){
        return "接收到的参数name:" + username;
    }

    // 传递数组
    @RequestMapping("/m6")
    public String m6(String[] arrayParam){
        return "接收到的参数arrayParam" + Arrays.toString(arrayParam) + ", 长度" + arrayParam.length;
    }

    // 传递集合
    @RequestMapping("/m7")
    public String m7(@RequestParam(required = false) List<String> listParam){
        return "接收到的参数listParam:" + listParam + ",长度：" +listParam.size();
    }
    //传递JSON
    // 只能接收请求正文中的内容
    @RequestMapping("/m8")
    public String m8(@RequestBody Person person){
        return "接收到的参数person:" + person.toString();
    }

    @RequestMapping("/m9/{userId}/{name}")
    public String m9(@PathVariable Integer userId, @PathVariable String name){
        return "userId:" + userId + ",name:" + name;
    }

    // 上传文件
    @RequestMapping("/m10")
    public String m10(@RequestPart MultipartFile file) throws IOException {
        // 打印文件名称
        System.out.println(file.getOriginalFilename());
        // 保存本地
        file.transferTo(new File("D:/临时，后续删除/" +file.getOriginalFilename()));
        return "success";
    }

    // servlet 方式获取cookie
    @RequestMapping("/getCookie")
    public String getCookie(HttpServletRequest request){
        // 拿到cookie
        Cookie[] cookies = request.getCookies();
//        for(Cookie cookie : cookies){
//            System.out.println(cookie.getName()+":"+cookie.getValue());
//        }
        // lam表达式，和上面循环意思一样
        if (cookies != null){
            // cookies为null时，会报空指针的异常，所以判断
            Arrays.stream(cookies).forEach(cookie -> {
                System.out.println(cookie.getName()+":"+cookie.getValue());
            });
        }
        return "获取cookie成功";
    }

    // SpingBoot获取cookie
    @RequestMapping("/getCookie2")
    public String getCookie2(@CookieValue String userId,@CookieValue String goodId){
        return "cookie存取的值userId:" + userId + ",gooId:" + goodId;
    }

    // servlet 方式获取 session  想要有session就需要先存session，session是服务端的，无法伪造
    @RequestMapping("/getSession")
    public String getSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            String userId = (String) session.getAttribute("userId");
            return "登录用户："+userId;
        }
        return "session 为空";
    }

    @RequestMapping("/setSession")
    public String setSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("userId", "6_6_6");
        return "success";
    }

    // Spring注解获取Session
    @RequestMapping("/getSession2")
    public String getSession2(@SessionAttribute(required = false) String userId){
        return "userId:" + userId;
    }

    // 使用HttpSession内置对象
    @RequestMapping("/getSession3")
    public String getSession3(HttpSession session){
            String userId = (String) session.getAttribute("userId");
            return "登录用户："+userId;
    }

    // servlet 获取 header
    @RequestMapping("/getHeader")
    public String getHeader(HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
        return "userAgent:" + userAgent;
    }

    // 通过注解 获取 header
    @RequestMapping("/getHeader2")
    public String getHeader2(@RequestHeader("User-Agent") String userAgent){
        return "userAgent:" + userAgent;
    }
}
