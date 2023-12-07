package com.example.demo.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

// 这是一个用于生成和验证 JSON Web Token（JWT）的 Java 工具类。JWT 是一种用于在不同系统之间安全传递信息的开放标准（RFC 7519）
public class JwtUtil {
    // SIGNAL 是一个用于生成和验证 JWT Token 的密钥，它应该是一个长、随机且保密的字符串。在这个工具类中，SIGNAL 被用作 HMAC256 签名算法的密钥
    // HMAC256 是一种基于哈希的消息认证码（HMAC）算法，用于在加密和验证消息时提供数据完整性和身份验证
    private static String SIGNAL = "1HU&**UUY**(GNH";
    /**
     * 生成token
     */
    // 方法用于生成 JWT Token，它接受一个 Map 参数，其中包含要添加到 Token 中的声明（claims）
    public static String getToken(Map<String, String> map){
        // 创建一个 Calendar 实例并获取当前时间
        Calendar instance = Calendar.getInstance();
        // 将当前时间增加 30 天，以设置令牌的过期时间
        instance.add(Calendar.DATE, 1);             //设置过期时间为30天

        // 创建 JWT Token 的构建器 JWTCreator.Builder
        final JWTCreator.Builder builder = JWT.create();
        //payload
        // 这段代码使用 Java 8 的 Lambda 表达式遍历传入的 map
        // 将 map 中的键值对添加到 JWT Token 的 "payload" 部分
        // JWT（JSON Web Token）令牌的 "payload" 部分是令牌的一部分，用于存储有关用户或其他相关信息的数据。它是一个 JSON 对象，包含了一组声明（claims）
        // 具体来说，它使用 builder.withClaim(k, v) 将 map 中的每个键值对添加到 JWT Token 中，其中 k 是键，v 是值
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        // builder.withExpiresAt(instance.getTime())：这一行代码设置了令牌的过期时间。
        // 首先，它获取了一个 Calendar 实例 instance，然后通过 instance.add(Calendar.DATE, 30) 将过期时间设置为当前时间的 30 天后，即令牌会在 30 天后过期。
        //.sign(Algorithm.HMAC256(SIGNAL))：这一行代码用于对 JWT 令牌进行签名。它调用了 builder 对象的 sign 方法，并传递了一个 Algorithm.HMAC256(SIGNAL) 参数。
        // 这表示使用 HMAC256 签名算法对令牌进行签名，并使用 SIGNAL 作为密钥。
        //Algorithm.HMAC256(SIGNAL)：这里使用 HMAC256 签名算法，需要提供一个密钥作为参数。密钥在你的代码中定义为 SIGNAL
        String token = builder.withExpiresAt(instance.getTime())//指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGNAL));//sign
        // return token 返回生成的 JWT 令牌，该令牌包括了过期时间和签名信息。这个令牌可以在需要验证用户身份的地方使用，以确保令牌的完整性和有效性
        return token;
    }

    // 这段代码用于验证 JWT 令牌的有效性和完整性。它接受一个 JWT 令牌作为输入，并使用预定义的密钥 SIGNAL 和 HMAC256 签名算法进行验证
    // 此函数的目的是确保传入的 JWT 令牌是有效的，并且签名正确，以防止伪造或篡改。如果验证通过，你可以进一步解析令牌以获取其中包含的信息，以验证用户的身份和权限
    public static DecodedJWT verify(String token){
        // JWT.require(Algorithm.HMAC256(SIGNAL))：这一部分创建了一个 JWT 验证器，通过调用 Algorithm.HMAC256(SIGNAL) 指定了要使用的签名算法和密钥。
        // 这表示令牌必须使用 HMAC256 签名算法，并且密钥必须与 SIGNAL 匹配，以便进行验证。
        //.build()：这一部分用于构建 JWT 验证器对象。
        //.verify(token)：最后，调用 verify 方法来验证传入的 JWT 令牌 token。如果令牌有效且签名正确，它将返回一个 DecodedJWT 对象，表示验证成功。
        // 否则，如果令牌无效或签名不正确，将引发异常
        return JWT.require(Algorithm.HMAC256(SIGNAL)).build().verify(token);
    }
}

