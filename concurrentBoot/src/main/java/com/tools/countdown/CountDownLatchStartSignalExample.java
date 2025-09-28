package com.tools.countdown;

/**
 * @Author ming.li
 * @Date 2025/9/24 9:04
 * @Version 1.0
 */
import java.util.concurrent.CountDownLatch;

public class CountDownLatchStartSignalExample {
    public static void main(String[] args) throws InterruptedException {
        // 计数器初始化为1，代表只需要等待一个"开始"信号
        CountDownLatch startSignal = new CountDownLatch(1);
        int runnerCount = 5; // 5个运动员

        // 创建并启动所有运动员线程
        for (int i = 0; i < runnerCount; i++) {
            final int runnerNumber = i + 1;
            new Thread(() -> {
                try {
                    System.out.println("运动员" + runnerNumber + "已就位，等待开始信号...");
                    // 等待开始信号，所有运动员都会在这里阻塞
                    startSignal.await();

                    // 收到开始信号后执行跑步逻辑
                    System.out.println("运动员" + runnerNumber + "开始跑步...");
                    // 模拟跑步过程
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println("运动员" + runnerNumber + "到达终点!");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }

        // 主线程模拟裁判准备工作
        System.out.println("裁判正在准备...");
        Thread.sleep(2000); // 准备2秒钟

        // 发出开始信号，计数器减为0，所有等待的线程被唤醒
        System.out.println("裁判发出开始信号!");
        startSignal.countDown();
    }
}
