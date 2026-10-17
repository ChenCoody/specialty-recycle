package com.example.lrb.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.example.lrb.pojo.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    //签名私钥
    private static String KEY = "23984d03-4f16-44e3-845e-1ef93f26ab96";
    //签名的失效时间 24小时
    private static Integer EXPIRATION_HOURS = 24;

    /**
     * 私钥加密token
     *
     * @param username 载荷中的数据
     * @param map      载荷中的数据
     * @return
     * @throws Exception
     */
    public static String generateToken(String username, Map<String, Object> map) {
        return Jwts.builder()
                .claim("userInfo", map)
                .setSubject(username)
                .setExpiration(Date.from(LocalDateTime.now().plusHours(EXPIRATION_HOURS).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    public static String generateToken(String username, Object data) {
        return Jwts.builder()
                .claim("data", toTokenData(data))
                .setSubject(username)
                .setExpiration(Date.from(LocalDateTime.now().plusHours(EXPIRATION_HOURS).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    /**
     * JWT 只保存鉴权需要的简单字段。避免把密码和 LocalDateTime 等对象写入 token，
     * 同时兼容 jjwt 0.9.x 内置的旧 Jackson 序列化器。
     */
    private static Object toTokenData(Object data) {
        if (!(data instanceof User)) {
            return data;
        }
        User user = (User) data;
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("id", user.getId());
        tokenData.put("name", user.getName());
        tokenData.put("avatar", user.getAvatar());
        tokenData.put("role", user.getRole() == null ? null : user.getRole().name());
        return tokenData;
    }


    /**
     * 解析token字符串
     */
    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

    /**
     * 获取jwt失效时间
     */
    public static Date getExpiration(String token) throws Exception {
        return parseToken(token).getExpiration();
    }

    public static Object getData(Claims claims) throws Exception {
        return claims.get("data");
    }
}
