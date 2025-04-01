package com.countDownLatchTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/9/13 9:06
 * @Version 1.0
 */
public class Demo1 {
    public static ThreadPoolExecutor t1=new ThreadPoolExecutor(10,10,200, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    public static void main(String[] args) {
        int a=10;
        CountDownLatch downLatch=new CountDownLatch(a);
        for (int i = 0; i <10 ; i++) {
            t1.execute(()->test(downLatch));
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("全部完成");
        t1.shutdown();
    }

    public static void test(CountDownLatch downLatch){
        System.out.println("进来了");
        downLatch.countDown();
    }
}
