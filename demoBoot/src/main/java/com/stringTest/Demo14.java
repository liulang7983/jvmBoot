package com.stringTest;

/**
 * @author ming.li
 * @date 2023/8/7 15:25
 */
public class Demo14 {
    public static void main(String[] args) {
        String str=",5";
        String[] split = str.split(",");
        System.out.println(split.length);
        System.out.println(split[0]);
        System.out.println(Integer.valueOf(split[0]));
        System.out.println(split[1]);


        String str1="4";
        String[] split1 = str1.split(",");
        System.out.println(split1[0]);
        System.out.println(split1.length);
    }
}
