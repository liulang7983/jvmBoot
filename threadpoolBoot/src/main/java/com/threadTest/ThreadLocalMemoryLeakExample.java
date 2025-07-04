package com.threadTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author ming.li
 * @Date 2025/6/30 13:41
 * @Version 1.0
 */
public class ThreadLocalMemoryLeakExample {
    private static ThreadLocal<BigObject> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // 任务1：设置ThreadLocal值
        executor.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            threadLocal.set(new BigObject()); // 创建大对象
            // 未调用threadLocal.remove()
        });

        // 任务1执行后，threadLocal被置为null，但线程池中的线程仍持有ThreadLocalMap
        threadLocal = null;

        // 任务2：新任务复用线程，但无法访问旧的ThreadLocal值
        executor.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            // 此时ThreadLocalMap中存在键为null但值不为null的Entry
            System.out.println(threadLocal.get()); // 输出null
        });

        executor.shutdown();
    }

    static class BigObject {
        private byte[] data = new byte[1024 * 1024]; // 1MB数据
    }
}
