package com.yg.edu.completableFutureTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author ming.li
 * @date 2023/11/27 20:10
 * thenAccept
 *第一个任务执行完成后，执行第二个回调方法任务，会将该任务的执行结果，作为入参，传递到回调方法中，但是回调方法是没有返回值的
 */
public class FutureThenAcceptTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("原始CompletableFuture方法任务");
                    return "捡田螺的小男孩";
                }
        );

        CompletableFuture thenAcceptFuture = orgFuture.thenAccept((a) -> {
            if ("捡田螺的小男孩".equals(a)) {
                System.out.println("关注了");
            }else {
                System.out.println("先考虑考虑");
            }
        });

        System.out.println(thenAcceptFuture.get());
    }
}
