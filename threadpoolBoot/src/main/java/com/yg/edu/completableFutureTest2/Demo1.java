package com.yg.edu.completableFutureTest2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @Author ming.li
 * @Date 2024/7/18 9:02
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //生成几个任务
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        futureList.add(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1 完成");
            return "任务1的数据";
        }));
        futureList.add(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2 完成");
            return "任务2的数据";
        }));
        futureList.add(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务3 完成");
            return "任务3的数据";
        }));
        //完成任务
        CompletableFuture<Void> allTask = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]))
                .whenComplete((t, e) -> {
                    System.out.println("所有任务都完成了， 返回结果集: "
                            + futureList.stream().map(CompletableFuture::join).collect(Collectors.joining(",")));
                });
        // 阻塞主线程
        allTask.join();
        System.out.println("main end, cost: " + (System.currentTimeMillis() - startTime));
    }
}
