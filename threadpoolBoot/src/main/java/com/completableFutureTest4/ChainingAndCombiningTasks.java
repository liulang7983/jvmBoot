package com.completableFutureTest4;

/**
 * @Author ming.li
 * @Date 2025/4/10 10:41
 * @Version 1.0
 */
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ChainingAndCombiningTasks {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        // 第一个异步任务
        CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        });

        // 链式调用，对第一个任务的结果进行转换
        CompletableFuture<String> secondTask = firstTask.thenApply(result -> result + " World");

        // 组合两个异步任务
        CompletableFuture<String> thirdTask = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "!";
        });

        CompletableFuture<String> combinedTask = secondTask.thenCombine(thirdTask, (s1, s2) -> s1 + s2);

        System.out.println(combinedTask.get());
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }
}
