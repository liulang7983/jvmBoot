package com.yg.edu.cyclicBarrierTest;

import java.util.concurrent.*;

/**
 * @Author ming.li
 * @Date 2024/9/13 9:13
 * @Version 1.0
 */
public class Demo2 {
    //
    public static ThreadPoolExecutor t1=new ThreadPoolExecutor(10,10,200, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(10);
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t1.execute(()->test(cyclicBarrier));
        }

    }
    public static void test(CyclicBarrier cyclicBarrier){
        System.out.println("我到了指定位置");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("开始执行");
    }
}
