//这是一个 JWT 拦截器类。
//
//        以下是这个拦截器类的详细解释：
//
//        JwtInterceptor - 这是一个实现了HandlerInterceptor接口的拦截器类，用于拦截请求并验证JWT令牌。
//        preHandle() 方法 - 该方法在请求处理之前执行，接收HttpServletRequest、HttpServletResponse和Object（处理器）作为参数。
//        map - 这是一个用于存储返回信息的Map对象。
//        token - 从请求头中获取名为"token"的令牌。
//        try-catch - 使用try-catch块对验证过程进行异常捕获。
//        JwtUtil.verify(token) - 调用JwtUtil的verify方法验证令牌的有效性。
//        异常处理 - 根据不同的异常类型，将相应的错误信息放入map中。
//        map.put(“state”, false) - 将状态信息设置为false。
//        json - 将map转换为JSON字符串。
//        设置响应头 - 设置响应的Content-Type为"application/json;charset=UTF-8"。
//        返回false - 返回false表示请求被拦截，不再继续执行。
//        其他情况下，返回true，表示请求继续执行。

package com.rabbiter.hospital.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.rabbiter.hospital.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String,Object> map = new HashMap<>();
        //获取请求头中的令牌
        String token = request.getHeader("token");
        try {
            JwtUtil.verify(token);//验证令牌
            return true;
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            map.put("msg", "无效签名！");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg", "token过期！");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg", "token算法不一致！");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg", "token无效！");
        }
        map.put("state", false);
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
