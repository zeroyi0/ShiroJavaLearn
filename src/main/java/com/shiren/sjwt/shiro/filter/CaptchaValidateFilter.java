package com.shiren.sjwt.shiro.filter;

import cn.hutool.captcha.ICaptcha;
import com.shiren.sjwt.common.constant.ShiroConstant;
import com.shiren.sjwt.common.util.ShiroUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CaptchaValidateFilter extends AccessControlFilter {


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 验证码禁用 或不是表单提交 允许访问
        if (!"post".equalsIgnoreCase(httpServletRequest.getMethod()))
        {
            return true;
        }
        return validateResponse(httpServletRequest, httpServletRequest.getParameter(ShiroConstant.CURRENT_VALIDATECODE_KEY));
    }

    private boolean validateResponse(HttpServletRequest httpServletRequest, String code) {
        Session session = ShiroUtils.getSession();
        ICaptcha captcha = (ICaptcha) session.getAttribute(ShiroConstant.CURRENT_CAPTCHA_KEY);
        if (StringUtils.isEmpty(code) || captcha == null) {
            return false;
        }
        return captcha.verify(code);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        servletRequest.setAttribute(ShiroConstant.CAPTCHA_STATUS_KEY, ShiroConstant.CAPTCHA_ERROR);
        return true;
    }
}
