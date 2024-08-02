package com.completableFutureTest;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author ming.li
 * @Date 2024/8/2 10:18
 * @Version 1.0
 */
public class Demo2 {

    //thenCombine / thenAcceptBoth / runAfterBoth都表示：将两个CompletableFuture组合起来，
    // 只有这两个都正常执行完了，才会执行某个任务。
    //区别在于：
    //thenCombine：会将两个任务的执行结果作为方法入参，传递到指定方法中，且有返回值
    //thenAcceptBoth: 会将两个任务的执行结果作为方法入参，传递到指定方法中，且无返回值
    //runAfterBoth 不会把执行结果当做方法入参，且没有返回值。
    public static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(3,3,2000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());

    //thenCombine：会将两个任务的执行结果作为方法入参，传递到指定方法中，且有返回值
    @Test
    public void test1()throws Exception{
        CompletableFuture<String> first = CompletableFuture.completedFuture("第一个异步任务");
        CompletableFuture<String> future = CompletableFuture
                //第二个异步任务
                .supplyAsync(() -> "第二个异步任务", threadPoolExecutor)
                // (w, s) -> System.out.println(s) 是第三个任务
                .thenCombineAsync(first, (s, w) -> {
                    System.out.println(w);
                    System.out.println(s);
                    return "两个异步任务的组合";
                }, threadPoolExecutor);
        System.out.println(future.join());
        threadPoolExecutor.shutdown();
    }

    //thenAcceptBoth: 会将两个任务的执行结果作为方法入参，传递到指定方法中，且无返回值
    @Test
    public void test2()throws Exception{
        CompletableFuture<String> first = CompletableFuture.completedFuture("第一个异步任务");
        CompletableFuture<Void> future = CompletableFuture
                //第二个异步任务
                .supplyAsync(() -> "第二个异步任务", threadPoolExecutor)
                // (w, s) -> System.out.println(s) 是第三个任务
                .thenAcceptBothAsync(first, (s, w) -> {
                    System.out.println(w);
                    System.out.println(s);
                }, threadPoolExecutor);
        System.out.println(future.join());
        threadPoolExecutor.shutdown();
    }
}
