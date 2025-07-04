package com.threadTest;

/**
 * @Author ming.li
 * @Date 2025/6/30 10:38
 * @Version 1.0
 */
public class Demo6 {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        try {
            thread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
