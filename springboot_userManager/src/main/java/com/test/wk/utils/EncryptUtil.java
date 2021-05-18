package com.test.wk.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author: Sparks
 * @Date: 2021/5/13 17:18
 * @Description
 */
public class EncryptUtil {

    public static String encryptBase64(String msg) throws UnsupportedEncodingException {
        String s = Base64.getEncoder().encodeToString(msg.getBytes("UTF-8"));
        return s;
    }

    public static String encryptBase64(String msg, String charset) throws UnsupportedEncodingException {
        String s = Base64.getEncoder().encodeToString(msg.getBytes(charset));
        return s;
    }

    public static String decryptBase64(String msg){
        byte[] decode = Base64.getDecoder().decode(msg);
        return new String(decode, Charset.forName("UTF-8"));
    }

    public static String decryptBase64(String msg,String charset){
        byte[] decode = Base64.getDecoder().decode(msg);
        return new String(decode, Charset.forName(charset));
    }
}
