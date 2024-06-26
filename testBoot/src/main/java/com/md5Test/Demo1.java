package com.md5Test;

import org.springframework.util.DigestUtils;

/**
 * @Author ming.li
 * @Date 2024/6/6 18:48
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        String originalString = "Hello, World!";
        System.out.println("Original String: " + originalString);
        System.out.println("MD5 hash: " + md5Hash(originalString));
    }

    public static String md5Hash(String input) {
        // 使用DigestUtils直接获取MD5的16进制表示
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }
}
