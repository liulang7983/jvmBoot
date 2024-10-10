package com.yg.edu.corePoolSizeTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/9/26 19:09
 * @Version 1.0
 */
public class Demo1 {
    //核心池为1或者核心池为0，要是队列比较大，那基本就成了单线程处理了，没有用到最大线程池数
    public static ThreadPoolExecutor t1=new ThreadPoolExecutor(2,2,200, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    public static ThreadPoolExecutor t2=new ThreadPoolExecutor(0,2,200, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    public static void main(String[] args) {
        test1();
        test2();
    }
    public static void test1(){
        CountDownLatch downLatch=new CountDownLatch(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            t1.execute(()->tt(downLatch));
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("test1:"+(end-start));
    }
    public static void test2(){
        CountDownLatch downLatch=new CountDownLatch(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            t2.execute(()->tt(downLatch));
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("test2:"+(end-start));
    }

    public static void tt(CountDownLatch downLatch){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            downLatch.countDown();
        }
    }
}
