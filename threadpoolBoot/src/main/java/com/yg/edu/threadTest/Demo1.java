package com.yg.edu.threadTest;

/**
 * @Author ming.li
 * @Date 2024/7/1 11:17
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.start();
        try {
            Thread.sleep(2030);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Demo1");
    }
}
