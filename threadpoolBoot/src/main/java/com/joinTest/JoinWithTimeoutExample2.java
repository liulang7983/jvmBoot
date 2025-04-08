package com.joinTest;

/**
 * @Author ming.li
 * @Date 2025/2/24 16:32
 * @Version 1.0
 */
public class JoinWithTimeoutExample2 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                // 模拟耗时操作
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程执行完毕");
        });

        thread.start();

        try {
            // 主线程等待子线程 执行完毕
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程继续执行");
    }
}
