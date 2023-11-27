package com.yg.edu.completableFutureTest;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ming.li
 * @date 2023/11/27 19:35
 */
public class CompletableFuture1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask(() -> {
            Thread.sleep(2000);
            return UUID.randomUUID().toString();
        });
        new Thread(futureTask).start();
        CompletableFuture.completedFuture(futureTask.get());
        System.out.println(futureTask.get());
    }
}
