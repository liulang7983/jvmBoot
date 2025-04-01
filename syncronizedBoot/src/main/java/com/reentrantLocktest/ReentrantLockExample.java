package com.reentrantLocktest;

/**
 * @Author ming.li
 * @Date 2025/2/19 16:07
 * @Version 1.0
 */
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void performTask() {
        // 获取锁
        lock.lock();
        try {
            // 执行临界区代码
            System.out.println(Thread.currentThread().getName() + " 进入临界区");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " 离开临界区");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // 释放锁，确保在任何情况下锁都会被释放
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        // 创建线程 1 并启动
        Thread t1 = new Thread(example::performTask, "Thread-1");
        // 创建线程 2 并启动
        Thread t2 = new Thread(example::performTask, "Thread-2");
        t1.start();
        t2.start();
    }
}
