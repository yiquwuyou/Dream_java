package com.example.demo.common;

import com.example.demo.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// 获取当前登录用户    因为经常要用到，每次方法都获取太麻烦，便单独抽出来
public class UserSessionTools {
    // 获取 userinfo
    public static UserInfo getLoginUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        // 此项目里其实不用if判断，因为 拦截器已经做好工作了
        // 但为了让它成为一个更完美的工具类，要能脱离拦截器也能单独工作

        // 可读性差，效率高   正经项目都这样写
        Object userObj = null;
        if(session != null && (userObj =session.getAttribute(ApplicationVariable.SESSION_KEY_USERINFO)) != null) {
            return (UserInfo) userObj;
        }

//        // 可读性好，但代码低效，取了两次 session
//        if(session != null && session.getAttribute(ApplicationVariable.SESSION_KEY_USERINFO) != null) {
//            return (UserInfo) session.getAttribute(ApplicationVariable.SESSION_KEY_USERINFO);
//        }

        return null;
    }
}
