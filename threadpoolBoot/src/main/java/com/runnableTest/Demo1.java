package com.runnableTest;

/**
 * @Author ming.li
 * @Date 2024/7/1 11:27
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable1());
        thread.start();
        System.out.println("Demo1");
    }
}
