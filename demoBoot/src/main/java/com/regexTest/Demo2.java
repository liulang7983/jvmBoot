package com.regexTest;

/**
 * @author ming.li
 * @date 2023/10/10 14:54
 * 是否为整数
 */
public class Demo2 {
    public static void main(String[] args) {
        //是否数字
        String regex="[+-]?[0-9]+?";
        String str="-12";
        boolean matches = str.matches(regex);
        System.out.println(matches);
    }
}
