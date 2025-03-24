package com.yg.edu.dealTest;

/**
 * @Author ming.li
 * @Date 2025/3/11 10:33
 * 当线程池中的任务存在相互依赖关系时，可能会导致死锁。例如，
 * 一个任务 TaskA 执行过程中需要等待另一个任务 TaskB 的结果，
 * 而 TaskB 又在等待线程池中有空闲线程来执行，
 * 此时如果线程池中的所有线程都被占用，就会形成死锁
 * @Version 1.0
 */
import java.util.concurrent.*;

public class ThreadPoolDeadlockExample {
    private static final ExecutorService executor = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        Future<String> future1 = executor.submit(() -> {
            System.out.println("Task A is running.");
            Future<String> future2 = executor.submit(() -> {
                System.out.println("Task B is running.");
                return "Result from Task B";
            });
            try {
                return future2.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        });
        try {
            System.out.println(future1.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
