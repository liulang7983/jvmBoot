package com.completableFutureTest4;

/**
 * @Author ming.li
 * @Date 2025/4/10 10:14
 * @Version 1.0
 */
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncTaskCreation {
    public static void main(String[] args) {
        // 创建一个固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 有返回值的异步任务
        CompletableFuture<String> supplyFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Result from supplyAsync");
            return "Result from supplyAsync";
        }, executor);

        // 无返回值的异步任务
        CompletableFuture<Void> runFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task from runAsync completed");
        }, executor);

        // 关闭线程池
        executor.shutdown();
        System.out.println("任务结束");
    }
}
