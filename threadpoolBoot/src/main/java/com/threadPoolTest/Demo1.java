package com.threadPoolTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/8/16 10:20
 * @Version 1.0
 */
public class Demo1 {
    public static ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(0,100,1000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());

    /**
     * 此时和单线程无异，因为线程池添加任务的时候，核心池为0，那么此时会启动一个保底线程执行任务
     * 然后又因为必须任务队列满才会添加核心池到最大线程池之间的线程，那么此时队列是无界也就是Integer.MAX
     * 300个任务不会填满他，所以全部放在任务队列，等保底任务执行他们
     * 此时相当于核心池为1
     * 相当于当线程执行了300个任务
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
            System.out.println(Thread.currentThread().getName()+"第:"+i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            downLatch.countDown();
        }
    }
}
