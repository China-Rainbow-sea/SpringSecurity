package com.rainbowsea.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 图片验证：工具类：CaptchaUtil

@Controller
@Slf4j
public class CaptchaController {

    /**
     * 获取图片验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/code/image")
    public void getCaptchaCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(200, 100, 2, 20);
        String code = circleCaptcha.getCode();
        log.info("生成的图片验证码: {}",code);
        //将验证码存储到 session中
        request.getSession().setAttribute("CAPTCHA_CODE",code);
        // 将图片写到响应流里
        ImageIO.write(circleCaptcha.getImage(),"JPEG",response.getOutputStream());
        //ImageIO.write(circleCaptcha.getImage(),"JPEG",response.getOutputStream());
        // 将图片写到响应流里，参数一，图片，参数2:图片格式，参数3:响应流

    }

}
