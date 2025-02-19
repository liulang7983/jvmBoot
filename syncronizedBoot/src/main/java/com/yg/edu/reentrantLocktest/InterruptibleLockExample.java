package com.yg.edu.reentrantLocktest;

/**
 * @Author ming.li
 * @Date 2025/2/19 16:23
 * @Version 1.0
 */
import java.util.concurrent.locks.ReentrantLock;

public class InterruptibleLockExample {
    private final ReentrantLock lock = new ReentrantLock();
   /*
   * 线程1秒后中中断，所以方法中等待五秒再执行不会继续跑
   * */
    public void performInterruptibleTask() throws InterruptedException {
        // 可中断地获取锁
        lock.lockInterruptibly();
        try {
            System.out.println(Thread.currentThread().getName() + " 获取到锁");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " 获取到锁五秒后");
        } finally {
            System.out.println(Thread.currentThread().getName() + " finally释放锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        InterruptibleLockExample example = new InterruptibleLockExample();
        Thread t1 = new Thread(() -> {
            try {
                example.performInterruptibleTask();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " 被中断");
            }
        }, "Thread-1");
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 中断线程
        t1.interrupt();
    }
}
