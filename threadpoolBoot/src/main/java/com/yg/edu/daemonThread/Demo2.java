package com.yg.edu.daemonThread;

import org.junit.Test;

/**
 * @Author ming.li
 * @Date 2025/3/28 10:12
 * main 方法和 test2 方法都不会等待 test1 方法中的线程执行完毕，是因为 test1 方法中创建的线程是守护线程。
 * 当所有用户线程结束运行时，JVM 会自动退出，而不管守护线程是否还在运行
 * @Version 1.0
 */
public class Demo2 {
    public static void main(String[] args) {
        test1();
        System.out.println("main执行完毕");
    }

    public static   void test1(){
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test1执行完毕");
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Test
    public void test2(){
        test1();
        System.out.println("test2执行完毕");
    }
}
