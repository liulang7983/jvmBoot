package com.semaphoreTest;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/9/13 10:12
 * @Version 1.0
 */
public class Demo1 {
    public static ThreadPoolExecutor t1=new ThreadPoolExecutor(10,10,200, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());

    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(2);
        for (int i = 0; i < 10; i++) {
            t1.execute(()->test(semaphore));
        }
    }
    public static void test(Semaphore semaphore){
        System.out.println("我进来了");
        try {
            semaphore.acquire();
            System.out.println("获取到了信号量");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("释放");
            semaphore.release();
        }
    }
}
