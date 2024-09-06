package com.test.replaceTest;

/**
 * @Author ming.li
 * @Date 2024/9/5 18:05
 * @Version 1.0
 */
public class Demo6 {
    public static void main(String[] args) {
        String ss="我ssfdsf*&%.$%&dsf张三";
        System.out.println(ss.replaceAll("\\pP|\\pS",""));
    }
}
