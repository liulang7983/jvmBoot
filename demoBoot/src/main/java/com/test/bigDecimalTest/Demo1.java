package com.test.bigDecimalTest;

/**
 * @Author ming.li
 * @Date 2025/1/8 9:46
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        Integer W=3507;
        Integer H=2480;
        float w=841.85f;
        float h=595.25f;
        double w1 = (double) W / w;
        double h1 = (double) H / h;
        System.out.println(w1);
        System.out.println(h1);
    }
}
