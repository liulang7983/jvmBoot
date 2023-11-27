package com.yg.edu.completableFutureTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author ming.li
 * @date 2023/11/27 20:00
 * thenRun
 * 没有参数传递
 * 调用thenRun方法执行第二个任务时，则第二个任务和第一个任务是共用同一个线程池。
 * 调用thenRunAsync执行第二个任务时，则第一个任务使用的是你自己传入的线程池，第二个任务使用的是ForkJoin线程池
 */
public class FutureThenRunTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("先执行第一个CompletableFuture方法任务");
                    return "捡田螺的小男孩";
                }
        );

        CompletableFuture thenRunFuture = orgFuture.thenRun(() -> {
            System.out.println("接着执行第二个任务");
        });

        System.out.println(thenRunFuture.get());
    }
}
