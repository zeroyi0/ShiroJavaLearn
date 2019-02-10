package com.shiren.sjwt.common.util;

import org.apache.shiro.crypto.hash.Sha256Hash;

public class CredentialsUtils {

    public static String sha256(String credentials, String salt) {
        return new Sha256Hash(credentials, salt, 4).toString();
    }

}
