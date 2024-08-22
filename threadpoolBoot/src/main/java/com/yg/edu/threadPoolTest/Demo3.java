package com.yg.edu.threadPoolTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/8/16 10:20
 * @Version 1.0
 */
public class Demo3 {
    public static ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(2,100,1000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());

    /**
     * 此时效果和前面的Demo1无异，相当于单线程
     * @param args
     */
    public static void main(String[] args) {
        CountDownLatch downLatch=new CountDownLatch(300);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 300; i++) {
            int a=i;
            poolExecutor.execute(()->test(a,downLatch));
        }
        int poolSize = poolExecutor.getPoolSize();
        System.out.println("运行中线程:"+poolSize);
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }
    public static void test(Integer i, CountDownLatch downLatch){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"第:"+i);
        downLatch.countDown();
    }
}
