package com.threadTest;

/**
 * @Author ming.li
 * @Date 2025/2/25 14:14
 * @Version 1.0
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("线程 " + Thread.currentThread().getName() + " 正在执行");
    }
}

public class ThreadPoolExample {
    public static void main(String[] args) {
        // 创建一个固定大小为 3 的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 50; i++) {
            MyTask myTask = new MyTask();
            System.out.println(myTask.toString());
            executorService.submit(myTask);
        }
        // 关闭线程池
        executorService.shutdown();
    }
}
