//这是一个 JwtUtil 类的代码片段，用于生成和验证 JWT（JSON Web Token）。它具有以下方法:
//
//        getToken(Map<String, String> map): 根据提供的键值对生成一个 JWT。
//        verify(String token): 验证给定的 JWT，并返回解码后的 JWT 对象。
//        在生成 JWT 时，它使用了一个 SIGNAL 字符串作为加密密钥，并将传入的键值对存储在 JWT 的 payload 中。过期时间被设置为当前时间加上30天。
//
//        在验证 JWT 时，它使用相同的 SIGNAL 字符串作为加密密钥，然后使用构建器模式建立 JWT 验证器，并调用 verify 方法进行验证。
//
//        请注意，该类使用了 Auth0 的 Java JWT 库实现 JWT 的生成和验证功能。

package com.rabbiter.hospital.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtil {
    private static String SIGNAL = "1HU&**UUY**(GNH";
    /**
     * 生成token
     */
    public static String getToken(Map<String, String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 30);             //设置过期时间为30天

        //创建jwt builder
        final JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())//指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGNAL));//sign
        return token;
    }

    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SIGNAL)).build().verify(token);
    }
}
