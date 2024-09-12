package com.test.lockTest;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author ming.li
 * @Date 2024/9/12 15:04
 * @Version 1.0
 */
public class Demo2 {
    public static ThreadPoolExecutor t1=new ThreadPoolExecutor(10,10,200, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    public static void main(String[] args) {

        ReentrantLock lock=new ReentrantLock();
        Condition condition = lock.newCondition();
        for (int i = 0; i <6 ; i++) {

        }
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
