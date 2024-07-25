package com.yg.edu.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ming.li
 * @date 2023/6/29 19:27
 */
public class CyclicBarrierDemo {
    //循环栅栏
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        new Thread(() -> {
            try {
                Thread.sleep(10_00);
                System.out.println("t1 在准备 ");
                cyclicBarrier.await();   // 等另外一个一个线程准备好 然后开始做事情
                System.out.println("t1开始运行");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(3_000);
                System.out.println("t2 在准备  ");
                cyclicBarrier.await(); // 等另外一个一个线程准备好 然后开始做事情
                System.out.println("t2开始运行");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println(" 裁判 在准备 ");
        cyclicBarrier.await();
        System.out.println(" 裁判鸣枪");
        while (true){
            if (cyclicBarrier.getNumberWaiting()==0){
                System.out.println(" 时间到: 开始比赛");
                return;
            }
        }
    }




}

