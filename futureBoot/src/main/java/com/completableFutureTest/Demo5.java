package com.completableFutureTest;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/8/2 10:18
 * @Version 1.0
 */
public class Demo5 {

    /**
     AnyOf
     任意一个任务执行完，就执行anyOf返回的CompletableFuture。
     如果执行的任务异常，anyOf的CompletableFuture，执行get方法，会抛出异常
     */
    public static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(3,3,2000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());

    @Test
    public void test1()throws Exception{
        CompletableFuture<Void> a = CompletableFuture.runAsync(()->{
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我执行完了");
        });
        CompletableFuture<Void> b = CompletableFuture.runAsync(() -> {
            System.out.println("我也执行完了");
        });
        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(a, b).whenComplete((m,k)->{
            System.out.println("finish");
        });
        anyOfFuture.join();
    }
}
