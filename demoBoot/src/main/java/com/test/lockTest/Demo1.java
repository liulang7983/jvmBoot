package com.test.lockTest;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author ming.li
 * @Date 2024/9/12 14:50
 * @Version 1.0
 */
public class Demo1 {
    public static ThreadPoolExecutor t1=new ThreadPoolExecutor(10,10,200, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    public static ThreadPoolExecutor t2=new ThreadPoolExecutor(10,10,200, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());

    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock(true);
        for (int i = 0; i < 10; i++) {
            t1.execute(()->test1(lock));
        }
        for (int i = 0; i < 10; i++) {
            t2.execute(()->test2(lock));
        }

    }
    public static void test1(ReentrantLock lock){
        try {
            lock.lock();
            Thread.sleep(2000);
        }catch (Exception e){
            e.fillInStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public static void test2(ReentrantLock lock){
        System.out.println("QueueLength:"+lock.getQueueLength());
        System.out.println("holdCount:"+lock.getHoldCount());
        System.out.println("hasQueuedThreads:"+lock.hasQueuedThreads());
        System.out.println("isFair:"+lock.isFair());

    }
}
