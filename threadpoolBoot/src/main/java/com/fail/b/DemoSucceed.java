
package com.fail.b;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2025/4/2 10:42
 * 此时test1调用的线程池是2个，所以他里面有个小任务调用test2死锁了，那也只会占用一个test1调用的线程池和一个teset2调用的线程池
 * 此时还有一个test1的线程池处理人任务，还两个test2的线程池处理任务
 * @Version 1.0
 */
public class DemoSucceed {
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 3000, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
    public static ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(3, 3, 3000, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> test1(finalI));
        }
    }
    public static void test1(Integer a) {
        System.out.println("test1开始:"+a);
        CountDownLatch downLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            try {
                //数据准备时间
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int finalI = i;
            threadPoolExecutor1.execute(() -> test2(downLatch, finalI,a));
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test1完成=============::"+a);
    }

    public static void test2(CountDownLatch downLatch, Integer x,Integer a) {
        if (x.equals(1)&&a.equals(1)){
            try {
                //模拟死锁
                Thread.sleep(4000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("test2完成：" + x);
        downLatch.countDown();
    }
}