package com.joinTest;

/**
 * @Author ming.li
 * @Date 2025/2/24 16:30
 * @Version 1.0
 */
public class MultipleJoinExample {
    public static void main(String[] args) {
        // 创建第一个线程
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程 1 执行: " + i);
            }
        });

        // 创建第二个线程
        Thread thread2 = new Thread(() -> {
            try {
                // 线程 2 等待线程 1 执行完毕
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程 2 执行: " + i);
            }
        });

        // 启动线程 1
        thread1.start();
        // 启动线程 2
        thread2.start();

        try {
            // 主线程等待线程 2 执行完毕
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程继续执行");
    }
}
