
package com.fail.demo1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2025/4/2 10:42
 * 此时test1和test2用的不同的线程池，所以不影响，正常执行
 * @Version 1.0
 */
public class DemoSucceed {
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 3000, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
    public static ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(3, 3, 3000, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            threadPoolExecutor.execute(() -> test1());
        }
    }

    public static void test1() {
        System.out.println(Thread.currentThread().getName()+":test1开始");
        CountDownLatch downLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            try {
                //数据准备时间
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int finalI = i;
            threadPoolExecutor1.execute(() -> test2(downLatch, finalI));
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":test1完成");
    }

    public static void test2(CountDownLatch downLatch, Integer x) {
        System.out.println(Thread.currentThread().getName()+":test2完成：" + x);
        downLatch.countDown();
    }
}

