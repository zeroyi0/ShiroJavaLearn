package com.shiren.sjwt.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api("测试")
public class SysIndexController {

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("info")
    @RequiresPermissions("admin:read")
    @ResponseBody
    public String info() {
        return "哈哈哈哈哈哈哈哈哈哈哈哈哈";
    }

}
