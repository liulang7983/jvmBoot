package com.queue;

import java.util.concurrent.DelayQueue;


public class DelayedQueueRun {
    //延迟队列
    public static void main(String[] args) {
        DelayQueue<MovieTiket> delayQueue = new DelayQueue<MovieTiket>();
        MovieTiket tiket = new MovieTiket("电影票0", 10000);
        delayQueue.put(tiket);
        MovieTiket tiket1 = new MovieTiket("电影票1", 5000);
        delayQueue.put(tiket1);
        MovieTiket tiket2 = new MovieTiket("电影票2", 8000);
        delayQueue.put(tiket2);
        System.out.println("message:--->入队完毕");

        while (delayQueue.size() > 0) {
            try {
                tiket = delayQueue.take();
                System.out.println("电影票出队:" + tiket.getMsg());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
