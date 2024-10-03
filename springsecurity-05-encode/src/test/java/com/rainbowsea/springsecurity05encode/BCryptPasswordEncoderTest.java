package com.rainbowsea.springsecurity05encode;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
public class BCryptPasswordEncoderTest {


    @Test
    void testBcrypt() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode1 = bCryptPasswordEncoder.encode("123");
        String encode2 = bCryptPasswordEncoder.encode("123");
        String encode3 = bCryptPasswordEncoder.encode("123");

 /*       System.out.println(encode1);
        System.out.println(encode2);
        System.out.println(encode3);*/

        log.info(encode1);
        log.info(encode2);
        log.info(encode3);


        // 对比方法：参数1，明文 参数2：密文
        boolean result1 = bCryptPasswordEncoder.matches("123", encode1);
        boolean result2 = bCryptPasswordEncoder.matches("123", encode2);
        boolean result3 = bCryptPasswordEncoder.matches("123", encode3);

        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);

        System.out.println(result1 + ":" + result2 + ":" + result3);

    }

}
