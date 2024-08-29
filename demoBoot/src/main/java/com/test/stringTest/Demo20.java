package com.test.stringTest;

/**
 * @Author ming.li
 * @Date 2024/8/26 15:33
 * @Version 1.0
 */
public class Demo20 {
    public static void main(String[] args) {
        String str = "hello!@#$%^world";
        str = str.replaceAll("[^a-zA-Z0-9]+", ""); // 只保留字母和数字
        System.out.println(str);

        String originalString = "abc123shznagpdfafj张三李四王五、赵六。田七汉字!@#$%^&*()";
        originalString = originalString.replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5]+", ""); // 只保留字母和数字

        //String filteredString = originalString.replaceAll("[^\\p{L}&&[^\\u4e00-\\u9fa5]&&\\p{N}]", "");
        System.out.println(originalString); // 输出: abc123汉字
    }
}
