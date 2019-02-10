package com.shiren.sjwt.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import com.shiren.sjwt.common.constant.ShiroConstant;
import com.shiren.sjwt.common.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@Api("验证码")
@RequestMapping("captcha")
public class SysCaptchaController {

    @GetMapping("captcha.jpg")
    public void captcha(ServletResponse response) {
        OutputStream out = null;
        try {
            // 获取输出流
            out = response.getOutputStream();
            // 生成验证码
            ICaptcha lineCaptcha = CaptchaUtil.createShearCaptcha(200, 100);
            // 验证码图片写出
            lineCaptcha.write(out);

            ShiroUtils.getSession().setAttribute(ShiroConstant.CURRENT_CAPTCHA_KEY, lineCaptcha);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭输出流
            IOUtils.closeQuietly(out);
        }
    }

}
