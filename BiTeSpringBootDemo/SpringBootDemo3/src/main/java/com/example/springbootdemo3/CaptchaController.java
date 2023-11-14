package com.example.springbootdemo3;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RequestMapping("/admin")
@RestController
public class CaptchaController {
    //企业标准(建议):
    //常量定义: key: 全部大写, 单词之间使用下划线分割  value: 通常是小写, 以下划线分割
    private static final String KAPTCHA_SESSION_KEY = "HOME_KAPTCHA_SESSION_KEY";
    private static final String KAPTCHA_SESSION_DATE = "HOME_KAPTCHA_SESSION_DATE";
    //验证码的有效时间:ms
    private static final Long SESSION_TIMEOUT = 60 * 1000L;
    //验证成功: true
    //验证失败: false

    /**
     *  1. 从Session中获取到生成的验证码
     *  2. 比对前端传递的验证码和Session中存储的是否一样
     */
    @RequestMapping("/check")
    public Boolean check(String captcha, HttpSession session){


        if (!StringUtils.hasLength(captcha)){
            return false;
        }
        //从Session中获取验证码
        String saveCaptcha = (String)session.getAttribute(KAPTCHA_SESSION_KEY);
        Date saveDate = (Date)session.getAttribute(KAPTCHA_SESSION_DATE);
        //比对验证码
        if (captcha.equals(saveCaptcha)){
            //比对日期
            if (saveDate==null || System.currentTimeMillis() - saveDate.getTime()<SESSION_TIMEOUT){
                return true;
            }
        }
        return false;
    }
}
