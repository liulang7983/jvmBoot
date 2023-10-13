package com.regexTest;

/**
 * @author ming.li
 * @date 2023/10/10 14:19
 */
public class Demo1 {
    public static void main(String[] args) {
        String str="ss张";
        String regex = "[\u4e00-\u9fa5]+";
        boolean matches = str.matches(".*" + regex + ".*");
        System.out.println(matches);
    }
}
