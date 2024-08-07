package com.test.replaceTest;

/**
 * @author ming.li
 * @date 2023/8/23 19:28
 */
public class Demo1 {
    public static void main(String[] args) {
        String str="￥10.00";
        str=str.replaceAll("￥","¥");
        System.out.println(str);
    }
}
