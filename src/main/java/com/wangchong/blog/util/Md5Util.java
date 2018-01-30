package com.wangchong.blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    public static String getMD5(String str){

        try {
            MessageDigest message= MessageDigest.getInstance("MD5");
            byte[] bytes = message.digest(str.getBytes("utf-8"));
            return bytesToHex(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String bytesToHex(byte[] bytes){
        StringBuffer str = new StringBuffer();
        // 把数组每一字节换成16进制连成md5字符串
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];

            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                str.append("0");
            }
            str.append(Integer.toHexString(digital));
        }
        return str.toString();
}

    public static void main(String[] args) {
        String s=getMD5("123456");
        System.out.println(s);
    }
}
