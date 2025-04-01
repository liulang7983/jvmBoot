package com.threadTest;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author ming.li
 * @Date 2024/8/6 10:56
 * @Version 1.0
 */
public class Demo3 {
    //顺序执行,t3先启动再sleep也是顺序
    private static Thread t1;
    private static Thread t2;
    private static Thread t3;
    public static void main(String[] args) {
        t1=new Thread(()->{
            System.out.println("运行t1");
            LockSupport.unpark(t2);
        });
        t2=new Thread(()->{
            LockSupport.park();
            System.out.println("运行t2");
            LockSupport.unpark(t3);
        });
        t3=new Thread(()->{
            LockSupport.park();
            System.out.println("运行t3");

        });

        t2.start();
        t3.start();
        System.out.println("启动了2,3");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
    }
}
