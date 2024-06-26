package com.md5Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author ming.li
 * @Date 2024/6/6 19:10
 * @Version 1.0
 */
public class MD5Util {

    /**
     * 加密算法
     */
    public static final String ENCRYPT_ALGORITHM = "MD5";

    /**
     * MD5加密
     *
     * 加密结果：
     *  string2MD5 ("") = d41d8cd98f00b204e9800998ecf8427e
     *	string2MD5 ("a") = 0cc175b9c0f1b6a831c399e269772661
     *	string2MD5 ("abc") = 900150983cd24fb0d6963f7d28e17f72
     *	string2MD5 ("message digest") = f96b697d7cb7938d525a2f31aaf161d0
     *	string2MD5 ("abcdefghijklmnopqrstuvwxyz") = c3fcd3d76192e4007dfb496cca67e13b
     *	string2MD5 ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789") = d174ab98d277d9f5a5611c2c9f419d9f
     *	string2MD5 ("12345678901234567890123456789012345678901234567890123456789012345678901234567890") = 57edf4a22be3c955ac49da2e2107b67a
     */
    public static String string2MD5(String inSrc) {
        String result = null;
        MessageDigest messagedigest = null;

        try {
            messagedigest = MessageDigest.getInstance(ENCRYPT_ALGORITHM);
            byte[] bytes = inSrc.getBytes();
            byte[] targ = messagedigest.digest(bytes);
            result = bintoascii(targ);
        } catch (NoSuchAlgorithmException nsaex) {
            throw new RuntimeException(nsaex);
        }

        return result;
    }

    /**
     * 将md5加密后的128位（bit）的结果转换成16进制字符表示的字符串
     * @param bySourceByte
     */
    private static String bintoascii(byte[] bySourceByte) {
        int len, i;
        byte tb;
        char high, tmp, low;
        String result = new String();
        len = bySourceByte.length;
        for (i = 0; i < len; i++) {
            tb = bySourceByte[i];

            tmp = (char) ((tb >>> 4) & 0x000f);
            if (tmp >= 10)
                high = (char) ('a' + tmp - 10);
            else
                high = (char) ('0' + tmp);
            result += high;
            tmp = (char) (tb & 0x000f);
            if (tmp >= 10)
                low = (char) ('a' + tmp - 10);
            else
                low = (char) ('0' + tmp);

            result += low;
        }
        return result;
    }

}


