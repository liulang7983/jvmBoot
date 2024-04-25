package com.replaceTest;

/**
 * @Author ming.li
 * @Date 2024/4/3 16:55
 * @Version 1.0
 */
public class Demo5 {

    public static void main(String[] args) {
        String text = "Hello, world! This is a sample .text.";

        // 使用正则表达式去除标点符号
        String result = text.replaceAll("\\p{P}", "");
        result=result.replaceAll("\\s+","");

        System.out.println(result);
    }
}
