package com.daemonThread;

import org.junit.Test;

/**
 * @Author ming.li
 * @Date 2025/3/28 10:12
 * 此时main方法会等test1执行完毕退出，test2不会
 * 总结来说，main 方法对应的主线程和 test1 方法创建的线程都是非守护线程，test2 本身不是线程，只是一个 JUnit 测试方法
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        test1();
        System.out.println("main执行完毕:"+Thread.currentThread().isDaemon());
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
        thread.start();
    }

    @Test
    public void test2(){
        test1();
        System.out.println("test2执行完毕:"+Thread.currentThread().isDaemon());
    }
}
