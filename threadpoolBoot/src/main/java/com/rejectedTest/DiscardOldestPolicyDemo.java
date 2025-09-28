package com.rejectedTest;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2025/9/28 9:08
 * @Version 1.0
 */
public class DiscardOldestPolicyDemo {
    public static ThreadPoolExecutor t1=new ThreadPoolExecutor(10,10,200, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(20),new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i <200 ; i++) {
                final Integer x=i;
                t1.execute(()->test(x));
            }
        }).start();
    }
    public static void test(Integer i){
        System.out.println(Thread.currentThread().getName()+" 我是:"+i);
    }
}
