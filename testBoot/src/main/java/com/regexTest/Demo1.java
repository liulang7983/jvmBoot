package com.regexTest;

import java.util.regex.Pattern;

public class Demo1 {
    public static void main(String[] args) {
        String regex = "[\\u4e00-\\u9fa5a-zA-Z]+";
        String s="111.";
        System.out.println(checkContainsChineseOrLetter(s));
    }

    private static boolean checkContainsChineseOrLetter(String inputStr) {
        if (inputStr == null || inputStr.isEmpty()) {
            return false;
        } else {
            // 使用正则表达式进行匹配
            String regex1 = "[\\u4e00-\\u9fa5]+.*";
            Pattern compile1 = Pattern.compile(regex1);
            boolean matches1 = compile1.matcher(inputStr).matches();
            String regex2 = "[a-zA-Z]+.*";
            Pattern compile2 = Pattern.compile(regex2);
            boolean matches2 = compile2.matcher(inputStr).matches();
            return matches1||matches2;
        }
    }
}
