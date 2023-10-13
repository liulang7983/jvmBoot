package com.regexTest;

/**
 * @author ming.li
 * @date 2023/10/10 14:54
 * 是否为整数
 */
public class Demo2 {
    public static void main(String[] args) {
        String str="-12";
        boolean matches = str.matches("[+-]?[0-9]+?");
        System.out.println(matches);
    }
}
