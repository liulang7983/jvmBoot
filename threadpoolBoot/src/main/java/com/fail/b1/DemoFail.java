package com.fail.b1;

import java.util.concurrent.*;

/**
 * @Author ming.li
 * @Date 2025/4/3 10:55
 * 此时也就数据为2的才会超时，但是因为他是单线程，他阻塞了，导致在任务队列里的任务也全部超时了，其实这些任务可以完成
 * @Version 1.0
 */
public class DemoFail {
    public static ThreadPoolExecutor threadPoolExecutor1=new ThreadPoolExecutor(10,10,100, TimeUnit.MINUTES,new LinkedBlockingDeque<>());
    public static ThreadPoolExecutor threadPoolExecutor2=new ThreadPoolExecutor(1,1,100, TimeUnit.MINUTES,new LinkedBlockingDeque<>());

    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            int finalI = i;
            threadPoolExecutor1.execute(()->test1(finalI));
        }
    }
    public static void test1(Integer x){
        Future<?> submit = threadPoolExecutor2.submit(() -> test2(x));
        try {
            submit.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException|ExecutionException|TimeoutException e) {
            submit.cancel(true);
        }
    }

    public static void test2(Integer x){
        System.out.println("test2进入:"+x);
        if (x.equals(2)){
            try {
                //任务耗时两秒
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("test2完成:"+x);
    }
}
