package com.regexTest;

/**
 * @Author ming.li
 * @Date 2024/6/7 15:15
 * @Version 1.0
 */
public class Demo5 {

    public static void main(String[] args) {
        String[] testStrings = {"123", "abc", "123abc", "abc123", "abc def", "!@#"};
        for (String str : testStrings) {
            System.out.println(str + " contains letters: " + containsLetters(str));
        }
    }

    public static boolean containsLetters(String str) {
        return str.matches(".*[a-zA-Z]+.*");
    }

}
