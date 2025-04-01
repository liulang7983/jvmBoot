package com.lockingDequeTest;

/**
 * @Author ming.li
 * @Date 2025/2/28 13:44
 * 生产者插入比消费者慢，所以消费者会阻塞
 * @Version 1.0
 */
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueExample2 {
    private static final int QUEUE_CAPACITY = 3;
    private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    public static void main(String[] args) {
        // 生产者线程
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put(i);
                    System.out.println("生产者添加元素 " + i + " 到队列，当前队列大小: " + queue.size());
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 消费者线程
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(500);
                    Integer element = queue.take();
                    System.out.println("消费者取出元素 " + element + "，当前队列大小: " + queue.size());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
