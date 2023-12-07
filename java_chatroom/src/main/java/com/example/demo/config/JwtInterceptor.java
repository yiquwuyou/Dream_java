package com.example.demo.config;


import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.model.UserJwt;
import com.example.demo.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

// 拦截器
// 实现 “HandlerInterceptor” 接口，这是Sping 拦截器的基本要求
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    // 在 “preHandle” 方法中，拦截请求的预处理逻辑，此方法会在请求到达处理器之前被调用
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截-----------------------拦截-----------------------拦截");
        if (request.getMethod().equals("OPTIONS")){
            return true;
        }
        Map<String,Object> map = new HashMap<>();
        //获取请求头中的令牌
        String token = request.getHeader("token");
        try {
            JwtUtil.verify(token);//验证令牌的有效性，可能抛出以下异常
            DecodedJWT decodedJWT = JwtUtil.verify(token);
            Map<String, Claim> claims = decodedJWT.getClaims();

            Map<String, String> userJwt = new HashMap<>();
            // 处理声明信息
            for (Map.Entry<String, Claim> entry : claims.entrySet()) {
                String claimName = entry.getKey();
                Claim claimValue = entry.getValue();

                // 例子：提取声明信息并以中文输出
                String claimAsString = claimValue.asString();
                userJwt.put(claimName, claimAsString);
            }
            UserJwt.setUsername(userJwt.get("username"));
            UserJwt.setUserId(Integer.parseInt(userJwt.get("userId")));
            log.info(UserJwt.printf());
            return true;
            // 根据不同的异常，设置不同的错误消息到“map”中，并将“state”设为“false”表示验证失败
        }catch (SignatureVerificationException e){  // 无效签名异常
            e.printStackTrace();
            map.put("msg", "无效签名！");
        }catch (TokenExpiredException e){           // 令牌过期异常
            e.printStackTrace();
            map.put("msg", "token过期！");
        }catch (AlgorithmMismatchException e){      // 令牌算法不一致异常
            e.printStackTrace();
            map.put("msg", "token算法不一致！");
        }catch (Exception e){                       // 其他异常
            e.printStackTrace();
            map.put("msg", "token无效！");
        }
        // 将名为“state”的键添加到“map”中，并将其值设置为“false”，表示请求的状态是失败的
        map.put("state", false);
        // 使用 Jackson 库的 ObjectMapper 对象将 map 转换为 JSON 字符串。writeValueAsString 方法将 Java 对象转换为 JSON 字符串
        String json = new ObjectMapper().writeValueAsString(map);
        // 设置 HTTP 响应的内容类型为 "application/json"，并指定字符编码为 UTF-8。这是告诉客户端响应的内容是 JSON 格式的数据
        response.setContentType("application/json;charset=UTF-8");
        // 这一行代码获取响应的输出流，并将 JSON 字符串写入输出流中，以便将其发送到客户端
        response.getWriter().println(json);
        return false;
    }
}

