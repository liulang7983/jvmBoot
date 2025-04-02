
package com.fail.a;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2025/4/2 10:42
 * main调用test1和test2用了同一个线程池，任务test2需要准备时间，此时线程核心池全部被test1占用
 * 但是test1调用test2，有CountDownLatch，需要teset2全部跑完才能继续往下走，此时已经没有线程来执行test2了
 * teset1等待teset2执行完继续往下走，test2在等test1执行完成后让出线程执行任务
 * @Version 1.0
 */
public class DemoFail {
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 3000, TimeUnit.MINUTES, new LinkedBlockingDeque<>());

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            threadPoolExecutor.execute(() -> test1());
        }
    }

    public static void test1() {
        System.out.println("test1开始");
        CountDownLatch downLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            try {
                //数据准备时间
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int finalI = i;
            threadPoolExecutor.execute(() -> test2(downLatch, finalI));
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test1完成");
    }

    public static void test2(CountDownLatch downLatch, Integer x) {
        System.out.println("test2完成：" + x);
        downLatch.countDown();
    }
}

