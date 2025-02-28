package com.yg.edu.prioritytest;

/**
 * @Author ming.li
 * @Date 2025/2/26 17:24
 * @Version 1.0
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

class PriorityTask implements Runnable, Comparable<PriorityTask> {
    private int priority;
    private String taskName;

    public PriorityTask(int priority, String taskName) {
        this.priority = priority;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("执行任务：" + taskName + "，优先级：" + priority);
    }

    @Override
    public int compareTo(PriorityTask other) {
        // 按照优先级进行比较，优先级高的排在前面
        return Integer.compare(other.priority, this.priority);
    }
}

public class PriorityThreadPoolExample {
    public static void main(String[] args) {
        // 创建一个使用优先级队列的线程池
        BlockingQueue<Runnable> priorityQueue = new PriorityBlockingQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3, r -> {
            Thread t = new Thread(r);
            // 可以根据需要设置线程的优先级
            t.setPriority(Thread.NORM_PRIORITY);
            return t;
        });

        // 提交不同优先级的任务
        executorService.submit(new PriorityTask(5, "任务1"));
        executorService.submit(new PriorityTask(3, "任务2"));
        executorService.submit(new PriorityTask(7, "任务3"));

        // 关闭线程池
        executorService.shutdown();
    }
}
