package com.stringTest;

/**
 * @author ming.li
 * @date 2023/10/9 18:37
 */
public class Demo15 {
    public static void main(String[] args) {
        String str="157.pdf【1】";
        int start = str.indexOf("【");
        System.out.println(str.substring(0,start));
        String str1="2.jpg-10";
        int i = str1.lastIndexOf("-");
        System.out.println(str1.substring(0,i));
        String str2="2.pdf";
        System.out.println(str2.substring(str2.lastIndexOf(".")+1));
    }


}
