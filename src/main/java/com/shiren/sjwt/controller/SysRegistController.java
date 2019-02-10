package com.shiren.sjwt.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api("注册")
@RequestMapping("accounts")
public class SysRegistController {

    @GetMapping("regist")
    public String regist() {
        return "regist";
    }

}
