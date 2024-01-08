package com.regexTest;

import java.util.regex.Pattern;

/**
 * @author ming.li
 * @date 2023/10/10 14:54
 * 是否为整数
 */
public class Demo2 {
    public static void main(String[] args) {
        //是否数字
        String regex="[+-]?[0-9]+?";
        String str="-12.1243";
        boolean matches = str.matches(regex);
        System.out.println(matches);
        System.out.println(isNumeric2(str));
        String ss="123,344,234,";
        System.out.println(ss.substring(0,ss.lastIndexOf(",")));


    }
    public static boolean isNumeric2(String str) {
        return str != null && str.matches("-?\\d+(\\.\\d+)?");
    }


}
