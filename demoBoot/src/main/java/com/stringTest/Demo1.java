package com.stringTest;

/**
 * @author ming.li
 * @date 2023/5/18 11:34
 */
public class Demo1 {
    public static void main(String[] args) {
        String s="ss,22,2,d,"+"\r\n";
        System.out.println(s.substring(0,s.length()-4));
        System.out.println("---");
    }
}
