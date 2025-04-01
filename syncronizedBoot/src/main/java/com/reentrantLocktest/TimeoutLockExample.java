package com.reentrantLocktest;

/**
 * @Author ming.li
 * @Date 2025/2/19 16:49
 * @Version 1.0
 */
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimeoutLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void performTimedTask() {
        try {
            // 在 2 秒内尝试获取锁
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 在超时时间内获取到锁");
                    Thread.sleep(3000);
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " 在超时时间内未获取到锁");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        TimeoutLockExample example = new TimeoutLockExample();
        Thread t1 = new Thread(example::performTimedTask, "Thread-1");
        Thread t2 = new Thread(example::performTimedTask, "Thread-2");
        t1.start();
        t2.start();
    }
}
