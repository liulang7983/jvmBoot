package com.completableFutureTest3;

/**
 * @Author ming.li
 * @Date 2025/4/10 10:06
 * @Version 1.0
 */
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建一个已经完成的 CompletableFuture
        CompletableFuture<String> completedFuture = CompletableFuture.completedFuture("Hello");
        System.out.println(completedFuture.get());

        // 创建一个异步执行的 CompletableFuture
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "World";
        });
        System.out.println(future.get());
    }
}
