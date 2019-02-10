package com.shiren.sjwt;

import com.shiren.sjwt.dao.UserDao;
import com.shiren.sjwt.shiro.config.ShiroConfig;
import com.shiren.sjwt.shiro.realm.ShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SjwtApplication.class)
public class SjwtApplicationTests {

/*
    @Autowired
    UserDao userDao;

    @Autowired
    ShiroRealm shiroRealm;

    @Autowired
    ShiroConfig shiroConfig;
*/

/*    @Test
    public void contextLoads() {
        System.out.println(shiroConfig);

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(shiroRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
        subject.login(token);
        System.out.println(subject.isAuthenticated());

    }*/

    @Test
    public void test() throws NoSuchAlgorithmException {
        String sha256 = new Sha256Hash("guest", ByteSource.Util.bytes("guest"), 4).toString();
        System.out.println(sha256);
    }

}

