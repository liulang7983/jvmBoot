package com.yg.edu.reentrantLocktest;

/**
 * @Author ming.li
 * @Date 2025/2/19 16:11
 * @Version 1.0
 */

import java.util.concurrent.locks.ReentrantLock;

public class FairnessExample {
    // 创建公平锁
    private static final ReentrantLock fairLock = new ReentrantLock(true);
    // 创建非公平锁
    private static final ReentrantLock nonFairLock = new ReentrantLock(false);

    /*
     * 此时公平锁的第一层循环可能由于start，顺序不会是0,1,2,3,4,但是第二层循环的顺序肯定是第一层循环一样
     * 因为第一层循环后肯定是再次获取锁，此时获取不到则按顺序添加到末尾，所以和第一层的执行顺序是一样的
     * 非公平锁则执行顺序比较杂乱
     * */
    public static void main(String[] args) {
        // 测试公平锁
        //testLock(fairLock, "Fair Lock");
        // 测试非公平锁
        testLock(nonFairLock, "Non-Fair Lock");
    }

    private static void testLock(ReentrantLock lock, String lockType) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    lock.lock();
                    try {
                        System.out.println(lockType + ": " + Thread.currentThread().getName() + " 获取锁");
                    } finally {
                        lock.unlock();
                    }
                }
            }, "Thread-" + i);
            thread.start();
        }
    }
}
