package com.replaceTest;

/**
 * @author ming.li
 * @date 2023/8/23 19:28
 */
public class Demo2 {
    public static void main(String[] args) {
        String str1=".*.";
        String str2="李四";
        String str="我是张三张三是我";
        System.out.println(str.replaceAll(str1,str2));

    }
}
