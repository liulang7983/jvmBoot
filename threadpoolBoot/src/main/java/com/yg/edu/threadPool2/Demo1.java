package com.yg.edu.threadPool2;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/11/20 8:50
 * 获取线程数
 * @Version 1.0
 */
public class Demo1 {
    public static ThreadPoolExecutor executor=new ThreadPoolExecutor(4,4,2000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    //此时前四个任务全被创建出来，之后的任务才是放队列，任务再去取，哪怕有空线程也是先进队列再取
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(executor.getPoolSize());
                }
            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
