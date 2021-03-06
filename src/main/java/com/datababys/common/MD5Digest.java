package com.datababys.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Digest {

    private static MessageDigest md5=null;

    public static String getDigest(String msg) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if(null == md5) {
            md5=MessageDigest.getInstance("MD5");
        }
        byte[] byteArray=null;
        byteArray=msg.getBytes();
        byte[] md5Bytes=md5.digest(byteArray);
        StringBuffer hexValue=new StringBuffer();
        for(int i=0; i < md5Bytes.length; i++) {
            int val=((int)md5Bytes[i]) & 0xff;
            if(val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
