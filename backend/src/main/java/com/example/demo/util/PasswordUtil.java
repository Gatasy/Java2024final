
/*
 * 用于密码加密（登录注册以及修改密码时注意使用）
 */


package com.example.demo.util;

import java.security.MessageDigest;

public class PasswordUtil {
    public static String encrypt(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte b:hashBytes){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
            
        } catch (Exception e) {
            System.err.println("密码加密出错");
            return null;
        }
    }
}
