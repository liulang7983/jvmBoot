package com.test.intTest;

/**
 * @author ming.li
 * @date 2023/7/26 17:57
 */
public class Demo2 {
    public static void main(String[] args) {
        String s="1.0";
        if (s.indexOf(".")>0){
            s=s.substring(0,s.lastIndexOf("."));
        }
        Integer integer = Integer.valueOf(s);
        System.out.println(integer);
    }
}
