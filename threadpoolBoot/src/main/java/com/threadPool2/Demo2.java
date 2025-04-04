package com.threadPool2;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/11/20 8:50
 * 获取运行中任务
 * @Version 1.0
 */
public class Demo2 {
    public static ThreadPoolExecutor executor=new ThreadPoolExecutor(4,4,2000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    //此时前四个任务全被创建出来，之后的任务才是放队列，任务再去取，哪怕有空任务也是先进队列再取
    // 此时有暂停所以运行中的只有一个，但是运行中每次都是一个
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(executor.getActiveCount());
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
