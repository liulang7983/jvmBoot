package com.completableFutureTest3;

/**
 * @Author ming.li
 * @Date 2025/4/10 10:11
 * @Version 1.0
 */
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExceptionExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Error occurred");
        }).exceptionally(ex -> {
            System.out.println("Exception caught: " + ex.getMessage());
            return "Default Value";
        });

        System.out.println(future.get());
    }
}
