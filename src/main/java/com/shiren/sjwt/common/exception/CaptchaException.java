package com.shiren.sjwt.common.exception;

public class CaptchaException extends Exception {

    public CaptchaException() {
        super("验证码输入有误！");
    }

}
