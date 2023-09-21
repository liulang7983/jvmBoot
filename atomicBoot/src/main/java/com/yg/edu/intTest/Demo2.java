package com.yg.edu.intTest;

/**
 * @author ming.li
 * @date 2023/5/17 13:52
 */
public class Demo2 {
    public static void main(String[] args) {
        String s="ss,ss1,ss2,ss3";
        String[] split = s.split(",");
        System.out.println(split.length);

        String s1="SS";
        System.out.println(s1.split(",").length);
    }
}
