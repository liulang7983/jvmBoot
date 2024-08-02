package com.completableFutureTest;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author ming.li
 * @Date 2024/8/2 10:18
 * @Version 1.0
 */
public class Demo4 {

    /**
     AllOf
     所有任务都执行完成后，才执行 allOf返回的CompletableFuture。
     如果任意一个任务异常，allOf的CompletableFuture，执行get方法，会抛出异常
     */
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
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(a, b).whenComplete((m,k)->{
            System.out.println("finish");
        });
        System.out.println(allOfFuture.get());
    }
}
