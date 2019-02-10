package com.shiren.sjwt.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;

public class ShiroUtils {

    public static Session getSession() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            return subject.getSession();
        }
        return null;
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    public static ServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

}
