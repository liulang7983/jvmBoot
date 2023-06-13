package com.stringTest;

/**
 * @author ming.li
 * @date 2023/6/13 17:08
 */
public class Demo3 {
    public static void main(String[] args) {
        String s="无锡市滨湖区太湖街道周新中路265号 0510-88987777";
        String replace = s.replace(" ", "");
        System.out.println(replace);
    }
}
