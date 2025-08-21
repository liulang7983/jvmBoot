package com.test.exceptionTest;

/**
 * @Author ming.li
 * @Date 2025/7/21 10:58
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        System.out.println(test1());
        System.out.println(test2());
    }
    public static Integer test1(){
        Integer x=1;
        try {
            int a=1/0;
            x=a;
            return x;
        } catch (Exception e) {
           x=-1;
           return x;
        }finally {
            x=100;
            return x;
        }
    }

    public static Integer test2(){
        Integer x=1;
        try {
            int a=2/1;
            x=a;
            return x;
        } catch (Exception e) {
            x=-1;
            return x;
        }finally {
            x=100;
            return x;
        }
    }
}
