package com.yg.edu.pool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ming.li
 * @date 2023/7/12 10:50
 */
public class OrderPool {
    public static void main(String[] args) {
        // 此时使用的是默认的AbortPolicy拒绝策略，抛出异常
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(4,8,2000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(40));
        for (int i = 0; i < 200; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadPoolExecutor.submit(new ThreadService(i,"我"));
        }
    }
}
