package com.yg.edu.joinTest;

/**
 * @Author ming.li
 * @Date 2025/2/24 16:25
 * @Version 1.0
 */
public class JoinExample {
    public static void main(String[] args) {
        // 创建一个新线程
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    // 模拟耗时操作
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程执行: " + i);
            }
        });

        // 启动子线程
        thread.start();

        try {
            // 主线程等待子线程执行完毕
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程继续执行");
    }
}
