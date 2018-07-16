package com.yanglf.usermanage.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static MessageDigest getMD5(){
        MessageDigest md5 = null;
        try {
            md5  = MessageDigest.getInstance("MD5");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    public static String encode(String str){
        MessageDigest md5 = getMD5();
        BigInteger integer = null;
        try {
            byte[] digest = md5.digest(str.getBytes("utf-8"));
             integer = new BigInteger(1,digest);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return integer.toString(16);
    }


}
