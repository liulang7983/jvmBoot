package com.rejectedTest;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2025/9/28 9:08
 * @Version 1.0
 */
public class DiscardPolicyDemo {
    public static ThreadPoolExecutor t1=new ThreadPoolExecutor(10,10,200, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(),new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i <200 ; i++) {
                final Integer x=i;
                t1.execute(()->test(x));
            }
        }).start();
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.shutdown();
    }
    public static void test(Integer i){
        System.out.println(Thread.currentThread().getName()+" 我是:"+i);
    }
}
