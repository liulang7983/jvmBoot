package com.yg.edu.threadPool2;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/11/20 8:50
 * @Version 1.0
 */
public class Demo3 {
    public static ThreadPoolExecutor executor=new ThreadPoolExecutor(0,4,2000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    //核心池为0，队列无界，则相当于单线程
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(executor.getPoolSize());
                }
            });
        }

    }
}
