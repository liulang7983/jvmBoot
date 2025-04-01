package com.threadTest;

/**
 * @Author ming.li
 * @Date 2024/7/1 11:17
 * @Version 1.0
 */
public class Thread1 extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread1");
    }
}
