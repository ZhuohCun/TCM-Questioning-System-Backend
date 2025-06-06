package com.InnovativePractice.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final String SECRET_KEY = "Zhuohcun'sJWTpassword!";

    //接收业务数据，生成token返回
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }
    //验证token，返回业务数据
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY)).
                build().
                verify(token).
                getClaim("claims").
                asMap();

    }
}
