package com.test.replaceTest;

/**
 * @author ming.li
 * @date 2023/10/9 11:37
 */
public class Demo4 {
    public static void main(String[] args) {
        String s="微信图片_20230519172258.jpg-3";
        int end = s.lastIndexOf("-");
        int start = s.lastIndexOf(".");
        String substring = s.substring(start, end);
        System.out.println(s.substring(0,start)+s.substring(end));
    }
}
