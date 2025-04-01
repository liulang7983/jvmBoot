package com.threadPoolTest;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/11/15 9:31
 * @Version 1.0
 */
public class Demo5 {
    public static ThreadPoolExecutor executor=new ThreadPoolExecutor(4,4,2000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    static {
        executor.allowCoreThreadTimeOut(true);
    }
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Integer x=i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("运行中线程数:"+executor.getActiveCount());
                    System.out.println(x);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
