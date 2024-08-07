package com.yg.edu.threadTest;

/**
 * @Author ming.li
 * @Date 2024/8/6 10:42
 * @Version 1.0
 */
public class Demo2 {
    //顺序执行,t3先启动再sleep则没用
    public static void main(String[] args)throws Exception {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(200);
                System.out.println("执行了t1");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
                Thread.sleep(200);
                System.out.println("执行了t2");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread t3 = new Thread(() -> {
            try {
                t2.join();
                Thread.sleep(200);
                System.out.println("执行了t3");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        t3.start();
        t2.start();
        t1.start();


    }
}
