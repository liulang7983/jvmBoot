package com.completableFutureTest4;

/**
 * @Author ming.li
 * @Date 2025/4/10 11:00
 * @Version 1.0
 */
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class WaitForAllTasks {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task 1 result";
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task 2 result";
        });

        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2);
        allTasks.get();

        System.out.println(task1.get());
        System.out.println(task2.get());
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }
}
