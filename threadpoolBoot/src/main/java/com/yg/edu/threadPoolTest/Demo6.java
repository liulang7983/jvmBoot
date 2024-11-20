package com.yg.edu.threadPoolTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/11/20 16:34
 * @Version 1.0
 * 线程池死锁
 */
public class Demo6 {
    public static ThreadPoolExecutor executor=new ThreadPoolExecutor(4,4,2000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    //此时任务1进入四个之后占满了所有的线程，但是他里面的任务2有CountDownLatch需要执行完所有的任务才放开继续往下
    //此时任务2没线程执行任务，任务1因为线程2没执行完不能结束，死锁了
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            executor.execute(()->test1());
        }
    }
    public static void test1(){
        System.out.println("进入任务1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CountDownLatch downLatch=new CountDownLatch(3);
        for (int i = 0; i <3 ; i++) {
            executor.execute(()->test2(downLatch));
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test2(CountDownLatch countDownLatch){
        System.out.println("进入任务2");
        countDownLatch.countDown();
    }
}
