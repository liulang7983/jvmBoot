package com.lock.fair;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author ming.li
 * @Date 2025/4/1 9:01
 * 非公平锁因为休眠一毫秒进的线程队列，所以是排队的，那么被获取也是顺序的，然后获取lock是非公平锁，他是多线程
 * 虽然99在98后面被线程拿到去尝试获取锁，但是99会直接尝试获取锁，不看是否有人排队，所以线程进来的时候可能刚好有人释放锁，
 * 他立马尝试加锁，所以打印是乱的
 * @Version 1.0
 */
public class Demo2 {
    public static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(20,20,300, TimeUnit.MINUTES,new LinkedBlockingDeque<>());

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(false);
        CountDownLatch downLatch = new CountDownLatch(1000);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1);
                int finalI = i;
                threadPoolExecutor.execute(()->test1(lock, finalI,downLatch));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }
    public static void test1(ReentrantLock lock,Integer i,CountDownLatch downLatch){
        try {
            lock.lock();
            Thread.sleep(2);
            System.out.println("执行到:"+i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            downLatch.countDown();
        }
    }
}
