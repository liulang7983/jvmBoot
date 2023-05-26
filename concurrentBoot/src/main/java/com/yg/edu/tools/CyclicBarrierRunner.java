package com.yg.edu.tools;

import java.util.concurrent.CyclicBarrier;


public class CyclicBarrierRunner implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private int index ;

    public CyclicBarrierRunner(CyclicBarrier cyclicBarrier, int index) {
        this.cyclicBarrier = cyclicBarrier;
        this.index = index;
    }

    public void run() {
        try {
            System.out.println("index: " + index);
            Thread.sleep(1000);
            cyclicBarrier.await();
            System.out.println("index等待结束: " + index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //此时CyclicBarrier有11个，那必须执行11个await才能继续往下走
    public static void main(String[] args) throws Exception {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(11, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有特工到达屏障，准备开始执行秘密任务");
            }
        });
        for (int i = 0; i < 10; i++) {
            new Thread(new CyclicBarrierRunner(cyclicBarrier, i)).start();
        }
        cyclicBarrier.await();
        System.out.println("全部到达屏障....1");

        Thread.sleep(5000);

        for (int i = 0; i < 10; i++) {
            new Thread(new CyclicBarrierRunner(cyclicBarrier, i)).start();
        }
        cyclicBarrier.await();
        System.out.println("全部到达屏障....1");
    }

}
