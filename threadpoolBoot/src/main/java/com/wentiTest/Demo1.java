package com.wentiTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/7/6 17:01
 * @Version 1.0
 */
public class Demo1 {
    /**
     * 线程池共用会导致卡住的问题，外部线程占住了所有的核心池和最大线程池
     * 里面的线程又由于有CountDownLatch，会等待线程池释放才执行，执行完了在继续往下,但是外面的线程需要里面的线程执行完才释放
     * 类似于互锁
     */
    public static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(4,4,2000,TimeUnit.SECONDS,new LinkedBlockingDeque<>());
    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            Integer x=i;
            threadPoolExecutor.execute(()->test1(x));
        }
    }

    public static void test1(Integer x){
        System.out.println("进入了1:"+x);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CountDownLatch countDownLatch=new CountDownLatch(1);
        threadPoolExecutor.execute(()->test2(x,countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("已完成1："+x);
    }
    public static void test2(Integer x,CountDownLatch countDownLatch){
        System.out.println("进入了2:"+x);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
    }
}
