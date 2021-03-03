package com.atguigu.commonutils;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JWTUtils {
    public static final long expire = 1000*60*60*24;
    public static final String appSecret = "umdefesfepose987";

    /**
     * 获取jwt token
     * @param id
     * @param nickName
     * @return
     */
    public static String getJWTToken(String id,String nickName){
        String JWTToken = Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .setSubject("guli_user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expire))
                .claim("id",id)
                .claim("nickname",nickName)
                .signWith(SignatureAlgorithm.HS256,appSecret)
                .compact();
        return JWTToken;
    }

    /**
     * 判断token是否存在于有效
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken){
        if(StringUtils.isEmpty(jwtToken)){
            return false;
        }
        try {
            Jwts.parser().setSigningKey(appSecret).parseClaimsJwt(jwtToken);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 判断token是否存在于有效
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request){
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)){
            return false;
        }
        try {
            Jwts.parser().setSigningKey(appSecret).parseClaimsJwt(jwtToken);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据jwtToken获取会员id
     * @param request
     * @return
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request){
        String jwtToken = request.getHeader("token");
        //String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJndWxpX3VzZXIiLCJpYXQiOjE2MTM3MTkzMDksImV4cCI6MTYxMzgwNTcwOSwiaWQiOiIxMjM0NTYiLCJuaWNrbmFtZSI6IuW8oOS4iSJ9._mVTuO8LlmiPx1b1xFziv35dATf6FE6RR3Fnj1g3Mjk";

        if(StringUtils.isEmpty(jwtToken)){
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(appSecret).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        String id = (String)claims.get("id");
        return id;
    }
}
