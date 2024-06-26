package com.stringTest;

/**
 * @Author ming.li
 * @Date 2024/5/12 14:17
 * @Version 1.0
 */
public class Demo2 {
    public static void main(String[] args) {
        String ss="123";
        String s1="3";
        int i = ss.indexOf(s1);
        System.out.println(i);
        int length = s1.length();
        if (i+length==ss.length()){
            System.out.println("尾巴");
        }else {
            System.out.println("不是尾巴");
        }
    }
}
