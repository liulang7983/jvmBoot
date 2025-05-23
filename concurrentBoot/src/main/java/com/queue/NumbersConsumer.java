package com.queue;

import java.util.concurrent.BlockingQueue;



public class NumbersConsumer implements Runnable {
    private BlockingQueue<Integer> queue;
    private final int poisonPill;

    public NumbersConsumer(BlockingQueue<Integer> queue, int poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }

    public void run() {
        try {
            while (true) {
                Integer number = queue.take();
                if (number.equals(poisonPill)) {
                    System.out.println("武大郎喝到毒药-"+Thread.currentThread().getId()+"号,喝药-编号:"+number);
                    return;
                }
                System.out.println("武大郎-"+Thread.currentThread().getId()+"号,喝药-编号:"+number);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
