package com.test.stringTest;

/**
 * @author ming.li
 * @date 2023/10/26 9:59
 */
public class Demo18 {
    public static void main(String[] args) {
        String filePath="C:\\Users\\ken\\Desktop\\1.txt张三";
        String s = filePath.replaceAll( "[^\u4e00-\u9fa5]", "");
        System.out.println(s);
    }

}
