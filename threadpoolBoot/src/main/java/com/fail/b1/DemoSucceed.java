package com.fail.b1;

import java.util.concurrent.*;

/**
 * @Author ming.li
 * @Date 2025/4/3 10:55
 * 此时threadPoolExecutor2会有超时限制，为了防止一个超时吧后续可以完成的任务也卡超时了
 * 此时我使用threadPoolExecutor3，单线程线程池保证每次只有一个任务进入threadPoolExecutor2
 * 只有上一个任务完成才会有下一个任务进入threadPoolExecutor2，那么此时test1Copy的任务就保证每个任务都是有1秒的超时时间
 * @Version 1.0
 */
public class DemoSucceed {
    public static ThreadPoolExecutor threadPoolExecutor1=new ThreadPoolExecutor(10,10,100, TimeUnit.MINUTES,new LinkedBlockingDeque<>());
    public static ThreadPoolExecutor threadPoolExecutor2=new ThreadPoolExecutor(1,1,100, TimeUnit.MINUTES,new LinkedBlockingDeque<>());
    public static ThreadPoolExecutor threadPoolExecutor3=new ThreadPoolExecutor(1,1,100, TimeUnit.MINUTES,new LinkedBlockingDeque<>());

    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            int finalI = i;
            threadPoolExecutor1.execute(()->test1(finalI));
        }
    }
    public static void test1(Integer x){
        Future<?> submit = threadPoolExecutor3.submit(() -> test1Copy(x));
    }
    public static void test1Copy(Integer x){
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
                System.out.println("test2完成:"+x);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("test2完成:"+x);
        }
    }
}
