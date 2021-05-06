package com.library.management.util;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
public class Md5Generator {

    public String generateMd5(String password) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] byteData = md.digest();
            for (byte byteDatum : byteData) {
                sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }


}
