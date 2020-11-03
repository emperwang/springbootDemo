package com.wk.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wk
 * @Date: 2020/11/3 16:43
 * @Description
 */

public class JwtUtil {


    public static final long EXPIREATION_TIME = 3600000000L;
    public static final String SECERT = "ThisIsSecret";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";

    public static String generateToken(){
        Map<String,Object> map = new HashMap<>();
        // 可以把任何安全的数据放到map中
        map.put("username", "zhangsan");
        final Date nnw = new Date();
        map.put("generateTime", nnw);
        final String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(nnw.getTime()+EXPIREATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECERT)
                .compact();
        return jwt;
    }

    public static Map<String,Object> validateToken(String token){
        Map<String, Object> resp = new HashMap<>();
        if (token != null){
            // 解析token
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECERT)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            String username = (String)body.get("username");
            Date generateTime = new Date((Long)body.get("generateTime"));
            resp.put("username",username);
            resp.put("generateTime", generateTime);
        }

        return resp;
    }

    public static void main(String[] args) {
        final String s = JwtUtil.generateToken();
        System.out.println("token: "+ s);
        final Map<String, Object> map = JwtUtil.validateToken(s);
        System.out.println(map.toString());

    }
}
