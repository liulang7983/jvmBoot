package com.stringTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ming.li
 * @date 2023/8/1 14:33
 */
public class Demo10 {
    //大字段替换
    public static void main(String[] args) {
        String requestBody="ssssssssss";
        String pattern = "(s*\"[^,\":]{500,}?\")";
        String s = requestBody.replaceAll(pattern, "\"*\"");
        List<String> list=new ArrayList<>();
        list.add("s");
        list.add("1");
        list.add("2");
        list.add("3");
        String join = String.join("", list);
        System.out.println(join);

    }

    public static String dataDesensitization(int start, int end, String value) {
        char[] chars;
        int i;
        if (start >= 0 && end + 1 <= value.length()) {
            chars = value.toCharArray();
            List<char[]> chars1 = Arrays.asList(chars);

            for (i = start; i < chars.length && i < end ; i++) {
                chars[i] = '*';
            }

            return new String(chars);
        } else if (start >= 0 && end >= value.length()) {
            chars = value.toCharArray();
            for (i = start; i < chars.length; i++) {
                chars[i] = '*';
            }

            return new String(chars);
        } else {
            return value;
        }
    }
}
