package com.test.regexTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo1 {
    public static void main(String[] args) {
        String regex = "[\\u4e00-\\u9fa5a-zA-Z]+";
        String s="5mg*20片";
        System.out.println(checkContainsChineseOrLetter(s));
        System.out.println(s.matches(".*[\\u4e00-\\u9fa5]+.*"));
    }

    private static boolean checkContainsChineseOrLetter(String inputStr) {
        String regex = ".*[a-zA-Z]+.*"; // 包含字母的正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputStr);

        boolean containsLetter = matcher.matches();

        if (containsLetter) {
            regex = ".*[\u4e00-\u9fa5]+.*"; // 包含中文的正则表达式
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(inputStr);
            boolean containsChinese = matcher.matches();

            return containsLetter && containsChinese;
        }
        return false;
    }
}
