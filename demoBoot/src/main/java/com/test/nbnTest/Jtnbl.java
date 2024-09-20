package com.test.nbnTest;

/**
 * @Author ming.li
 * @Date 2024/9/18 17:21
 * 静态内部类
 * @Version 1.0
 */
public class Jtnbl {
    private static int a;
    private int b;
    public static class Inner {
        public void print() {
            System.out.println(a);
        }
    }
    public void test(){
        System.out.println("test");
    }
}
