package com.InterruptTest;

/**
 * @Author ming.li
 * @Date 2025/9/11 13:58
 * @Version 1.0
 */
public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            // 循环执行，通过isInterrupted()检查中断状态
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("线程运行中...");
                try {
                    Thread.sleep(1000); // 阻塞状态，可能被中断
                } catch (InterruptedException e) {
                    System.out.println("捕获到中断异常，线程将退出");
                    // 注意：此时中断状态已被清除（变为false），若需要退出，可手动再次中断
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("线程退出");
        });

        t.start();
        Thread.sleep(3000); // 主线程等待3秒
        System.out.println("主线程调用中断");
        t.interrupt(); // 中断t线程
    }
}
