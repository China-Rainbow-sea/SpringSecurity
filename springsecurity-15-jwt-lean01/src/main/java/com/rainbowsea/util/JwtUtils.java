package com.rainbowsea.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtUtils {

    private static final String secret = "thomas";  // 密钥


    public String createJwt(Integer userId, String userName, List<String> authList) {

        Date issDate = new Date();  // 签发时间
        Date expireDate = new Date(issDate.getTime() + 1000 * 60 * 60 * 2); // 当前时间加上两个小时
        //Date expireDate = new Date(issDate.getTime() + 1000 * 30); // 过期时间 30秒

        // 头部
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("alg","HS256");
        headerClaims.put("typ","JWT");
        return JWT.create().withHeader(headerClaims)
                .withIssuer("rainbowsea")  // 设置签发人
                .withIssuedAt(issDate)  // 签发时间
                .withExpiresAt(expireDate)
                .withClaim("userId",userId)  // 自定义声明
                .withClaim("userName",userName) // 自定义声明
                .withClaim("userAuth",authList) // 自定义声明
                .sign(Algorithm.HMAC256(secret)); // 使用 HS256进行签名，使用 secret 作为密钥

    }

    public boolean verifyToken(String jwtToken) {
        // 创建校验器
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
            // 校验 token 是否正确
            DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);

            System.out.println("token 验证正确");
            //Integer userId = decodedJWT.getClaim("userId").asInt();
            //String userName = decodedJWT.getClaim("userName").asString();
            //List<String> userAuth = decodedJWT.getClaim("userAuth").asList(String.class);

            return true;
        } catch (Exception e) {
            System.out.println("token验证不正确");
            return false;
        }

    }


    /**
     * 从 jwt 的 payLoad 里获取声明，获取的用户id
     * @param jwt
     * @return
     */
    public Integer getUserIdFromToken(String jwt) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
            return decodedJWT.getClaim("userId").asInt();
        } catch (IllegalArgumentException e) {
            return -1;
        } catch (JWTVerificationException e) {
            return -1;
        }
    }


    /**
     * 从 jwt 的 payload里获取声明，获取的用户名
     */
    public String getUserNameFromToken(String jwt) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
            return decodedJWT.getClaim("userName").asString();
        } catch (IllegalArgumentException e) {
            return "";
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    /**
     * 从 jwt 的 payload 里获取声明，获取的用户的权限
     * @param jwt
     * @return
     */
    public List<String> getUserAuthFromToken(String jwt) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
            return decodedJWT.getClaim("userAuth").asList(String.class);
        } catch (IllegalArgumentException e) {
            return null;
        } catch (JWTVerificationException e) {
            return null;
        }

    }
}
