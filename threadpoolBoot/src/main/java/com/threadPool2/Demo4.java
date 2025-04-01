package com.threadPool2;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/11/20 8:50
 * @Version 1.0
 */
public class Demo4 {
    public static ThreadPoolExecutor executor=new ThreadPoolExecutor(4,8,2000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    //核心池为4，队列无界，队列放不满，则核心池肯定全部创建，最大线程不会创建
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("已创建线程数:"+executor.getPoolSize());
                    System.out.println("已提交任务数:"+executor.getTaskCount());
                    System.out.println("已完成任务数:"+executor.getCompletedTaskCount());
                    System.out.println();
                }
            });
        }

    }
}
