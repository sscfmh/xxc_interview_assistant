package com.xxc.xia.common.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/1/12 20:10
 */
public class JWTTokenUtils {
    /**
     * 创建token
     *
     * @param user
     * @param exp    指定失效时间
     * @param secret 指定秘钥
     * @return
     */
    public static String createToken(Object user, String userField, Date exp, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withClaim(userField, JSON.toJSONString(user)).withExpiresAt(exp)
            .sign(algorithm);
    }

    /**
     * 解析token，返回token中存储的用户信息，token不合法返回null
     *
     * @param token
     * @param secret 指定秘钥
     * @return
     */
    public static <T> T verifyToken(String token, String userField, String secret, Class<T> clazz) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return JSON.parseObject(decodedJWT.getClaim(userField).asString(), clazz);
        } catch (Exception e) {
            // token过期或者不合法
        }
        return null;
    }
}
