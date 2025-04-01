package com.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;



public class NumbersProducer implements Runnable {
    private BlockingQueue<Integer> numbersQueue;
    private final int poisonPill;
    private final int poisonPillPerProducer;

    public NumbersProducer(BlockingQueue<Integer> numbersQueue, int poisonPill, int poisonPillPerProducer) {
        this.numbersQueue = numbersQueue;
        this.poisonPill = poisonPill;
        this.poisonPillPerProducer = poisonPillPerProducer;
    }
    public void run() {
        try {
            generateNumbers();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void generateNumbers() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            numbersQueue.put(ThreadLocalRandom.current().nextInt(100));
            System.out.println("潘金莲-"+Thread.currentThread().getId()+"号,给武大郎的泡药！");
        }
        /*while(true){
            numbersQueue.put(ThreadLocalRandom.current().nextInt(100));
            if(false){break;}
        }*/

        for (int j = 0; j < poisonPillPerProducer; j++) {
            numbersQueue.put(poisonPill);
            System.out.println("潘金莲-"+Thread.currentThread().getId()+"号,往武大郎的药里放入第"+j+1+"颗毒丸！");
        }
    }
}
