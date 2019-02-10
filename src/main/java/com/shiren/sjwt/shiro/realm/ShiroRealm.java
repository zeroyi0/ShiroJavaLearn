package com.shiren.sjwt.shiro.realm;

import com.shiren.sjwt.common.exception.CaptchaException;
import com.shiren.sjwt.dao.entity.Permission;
import com.shiren.sjwt.dao.entity.Role;
import com.shiren.sjwt.dao.entity.User;
import com.shiren.sjwt.service.SysLoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysLoginService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        System.out.println("权限认证");
        if (user == null) {
            return null;
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Role> roles = user.getRoles();
        List<Permission> permissions;
        for (Role role :
                roles) {
            info.addRole(role.getRoleName());
            permissions = role.getPermissions();
            for (Permission permission :
                    permissions) {
                info.addStringPermission(permission.getPermission());
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        if (StringUtils.isEmpty(userName)) {
            return null;
        }

        User user = null;
        try {
            user = loginService.login(userName);
        } catch (AccountNotFoundException e) {
            throw new AuthenticationException(e.getMessage());
        }

        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassWord(), ByteSource.Util.bytes(userName), getName());
    }
}
