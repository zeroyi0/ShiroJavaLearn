package com.shiren.sjwt.controller;

import com.shiren.sjwt.model.Result;
import com.shiren.sjwt.model.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@Api("用户")
@RequestMapping("accounts")
public class SysLoginController {

    @ResponseBody
    @ApiOperation("login")
    @PostMapping("/login")
    public Result login(String userName, String userPwd, boolean rememberMe) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPwd)) {
            return Result.error("用户名或密码不能为空！");
        }
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(userName, userPwd, rememberMe));
        } catch (UnknownAccountException e) {
            return Result.error("账号不存在！");
        } catch (IncorrectCredentialsException e) {
            return Result.error("密码输入有误！");
        } catch (ExcessiveAttemptsException e) {
            return Result.error("错误次数过多！");
        } catch (AuthorizationException e) {
            return Result.error(e.getMessage());
        }
        return Result.ok();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "/login";
    }

}
