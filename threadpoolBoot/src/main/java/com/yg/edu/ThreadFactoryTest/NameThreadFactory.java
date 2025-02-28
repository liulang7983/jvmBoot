package com.yg.edu.ThreadFactoryTest;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author ming.li
 * @Date 2025/2/25 14:37
 * @Version 1.0
 */
public class NameThreadFactory implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "ThreadFactoryDemo1-" + threadNumber.getAndIncrement());
    }
}
