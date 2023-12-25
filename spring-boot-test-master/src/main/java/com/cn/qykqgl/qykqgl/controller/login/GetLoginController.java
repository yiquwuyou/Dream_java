package com.cn.qykqgl.qykqgl.controller.login;

import com.cn.qykqgl.qykqgl.dao.UserDao;
import com.cn.qykqgl.qykqgl.entity.Sta;
import com.cn.qykqgl.qykqgl.entity.User;
import com.cn.qykqgl.qykqgl.service.DlrzxxService;
import com.cn.qykqgl.qykqgl.service.StaService;
import com.cn.qykqgl.qykqgl.service.UserService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class GetLoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private StaService staService;
    @Autowired
    private DlrzxxService dlrzxxService;

    /**登录学生考勤管理系统*/
    @PostMapping("getLogin")
    public void getLogin(User user, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        JSONObject jobject = null;
        Map<String,Object> map;
        try{
            out = response.getWriter();
            String username = user.getUsername();
            String password = user.getPassword();
            System.out.println(username+";"+password);
            map = userService.getLogin(username,password);
            System.out.println(map);
            jobject = new JSONObject();
            if(map!=null){
                String name = map.get("name")+"";
                dlrzxxService.add_Dlrzxx(name);
                jobject.put("name", map.get("name"));
                jobject.put("id", map.get("id"));
            }
            out.print(jobject.toString());
        }catch (Exception e){
            jobject = new JSONObject();
            jobject.put("errorCode", "401");
            jobject.put("data", "");
            out.print(jobject.toString());
            e.printStackTrace();
        }
    }


    /**登录打卡系统*/
    @PostMapping("get_StaLogin")
    public void get_StaLogin(Sta sta, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        JSONObject jobject = new JSONObject();
        Map<String,Object> map;
        try{
            out = response.getWriter();
            String xm = sta.getXm();
            String zjhm = sta.getZjhm();
            System.out.println(xm+zjhm);
            map = staService.get_StaLogin(xm,zjhm);
            System.out.println(map.get("xm"));
            if(null!=map){
                jobject.put("xm", map.get("xm"));
                jobject.put("zjhm", map.get("zjhm"));
                jobject.put("gh", map.get("gh"));
                jobject.put("ssbm", map.get("ssbm"));
            }
            out.print(jobject.toString());
        }catch (Exception e){
            jobject = new JSONObject();
            jobject.put("errorCode", "401");
            jobject.put("data", "");
            out.print(jobject.toString());
            e.printStackTrace();
        }
    }
}
