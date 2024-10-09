package com.rainbowsea.util;

import java.util.Arrays;
import java.util.List;

public class JwtTest {
    public static void main(String[] args) {
/*        JwtUtils jwtUtils = new JwtUtils();
        List<String> authList = Arrays.asList("student:query", "student:add", "student:update");
        String myCreateJWt = jwtUtils.createJwt(19, "obama", authList);
        System.out.println(myCreateJWt);

        boolean verifyResult = jwtUtils.verifyToken(myCreateJWt);
        System.out.println(verifyResult);*/

        JwtTest jwtTest = new JwtTest();
        String token = jwtTest.createToken();
        JwtUtils jwtUtils = new JwtUtils();
        boolean verifyResult = jwtUtils.verifyToken(token);
        if(verifyResult) {
            // 从 token获取权限
            List<String> authList = jwtUtils.getUserAuthFromToken(token);
            System.out.println(authList);
        }



    }

    public String createToken() {
        JwtUtils jwtUtils = new JwtUtils();
        List<String> authList = Arrays.asList("student:query", "student:add", "student:update");
        String myCreateJWt = jwtUtils.createJwt(19, "obama", authList);
        System.out.println(myCreateJWt);
        return myCreateJWt;
    }


    public void verifyToken(String token) {
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyQXV0aCI6WyJzdHVkZW50OnF1ZXJ5Iiwic3R1ZGVudDphZGQiLCJzdHVkZW50OnVwZGF0ZSJdLCJpc3MiOiJyYWluYm93c2VhIiwiZXhwIjoxNzI4NDc5NTE5LCJ1c2VyTmFtZSI6Im9iYW1hIiwiaWF0IjoxNzI4NDcyMzE5LCJ1c2VySWQiOjE5fQ.sQfuTaEsi0BQGpQXXQwuzKwkkU4CnqUcX-ZNDRL7kUU";
        JwtUtils jwtUtils = new JwtUtils();

        // 校验验证
        boolean verifyResult = jwtUtils.verifyToken(jwt);
        System.out.println(verifyResult);
    }
}
