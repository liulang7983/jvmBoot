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
public class Demo4 {
    public static ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(40,100,1000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(200));

    /**
     * 此时查看线程池则可以看到不止核心池，还会有最大线程池，同时可以查看任务队列中的任务
     * 此时可以看到队列长度小于200的时候运行的线程池一直是核心池的大小也就是40，
     * 当等于200的时候，此时运行线程就开始增大，
     * 此时存在两种可能，
     * 1.当运行线程池等于最大线程池的时候，此时再添加任务则执行拒绝策略
     * 2.还没到最大线程的时候任务添加完成，队列开始从200往下降，此时运行线程池开始不变
     * @param args
     */
    public static void main(String[] args) {
        CountDownLatch downLatch=new CountDownLatch(500);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500; i++) {
            int a=i;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            poolExecutor.execute(()->test(a,downLatch));
            int poolSize = poolExecutor.getPoolSize();
            int size = poolExecutor.getQueue().size();
            System.out.println("运行中线程:"+poolSize);
            System.out.println("线程池中的队列长度:"+size);
        }
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
            Thread.sleep(130);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"第:"+i);
        downLatch.countDown();
    }
}
