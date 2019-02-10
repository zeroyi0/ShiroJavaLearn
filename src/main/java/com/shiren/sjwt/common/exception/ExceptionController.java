package com.shiren.sjwt.common.exception;

import com.shiren.sjwt.model.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AuthorizationException.class)
    public Result authorizationException() {
        return Result.error("无权限访问，请联系你爸爸");
    }

}
