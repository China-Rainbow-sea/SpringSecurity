package com.rainbowsea.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component  // 加入到 Spring IOC 容器当中
@Slf4j
public class JwtUtils {

    @Value("${my.secretKey}")
    private   String secret ;  // 密钥从, application.yaml 当中导入


    public String createJwt(String userInfo, List<String> authList) {

        Date issDate = new Date();  // 签发时间
        Date expireDate = new Date(issDate.getTime() + 1000 * 60 * 60 * 2); // 当前时间加上两个小时
        //Date expireDate = new Date(issDate.getTime() + 1000 * 30); // 过期时间 30秒

        // 头部
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("alg","HS256");
        headerClaims.put("typ","JWT");  // base64 jwt 头部所需的内容
        return JWT.create().withHeader(headerClaims)
                .withIssuer("rainbowsea")  // 设置签发人
                .withIssuedAt(issDate)  // 签发时间
                .withExpiresAt(expireDate)
                .withClaim("user_info",userInfo)  // 自定义声明
                .withClaim("userAuth",authList) // 自定义声明
                .sign(Algorithm.HMAC256(secret)); // 使用 HS256进行签名，使用 secret 作为密钥

    }

    public boolean verifyToken(String jwtToken) {
        // 创建校验器
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
            // 校验 token 是否正确
            DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);

           log.info("token 验证正确");
            //Integer userId = decodedJWT.getClaim("userId").asInt();
            //String userName = decodedJWT.getClaim("userName").asString();
            //List<String> userAuth = decodedJWT.getClaim("userAuth").asList(String.class);

            return true;
        } catch (Exception e) {
            log.info("token验证不正确");
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
     * 从 jwt 的 payload里获取声明，获取的用户的信息
     */
    public String getUserInfoFromToken(String jwt) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
            return decodedJWT.getClaim("user_info").asString();
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
