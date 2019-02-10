package com.shiren.sjwt.service;

import com.shiren.sjwt.common.constant.ShiroConstant;
import com.shiren.sjwt.common.exception.CaptchaException;
import com.shiren.sjwt.common.util.ShiroUtils;
import com.shiren.sjwt.dao.UserDao;
import com.shiren.sjwt.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class SysLoginService {

    @Autowired
    UserDao userDao;

    public User login(String userName) throws AccountNotFoundException {
        if (StringUtils.isEmpty(userName)) {
            throw new AccountNotFoundException();
        }
        /*
        if (ShiroConstant.CAPTCHA_ERROR.equalsIgnoreCase((String) ShiroUtils.getRequest().getAttribute(ShiroConstant.CAPTCHA_STATUS_KEY))) {
            throw new CaptchaException();
        }
        */
        return userDao.findUserByUserName(userName);
    }

}
