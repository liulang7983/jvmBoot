package com.yg.edu.cyclicBarrierTest;

import java.util.concurrent.*;

/**
 * @Author ming.li
 * @Date 2024/9/13 9:13
 * @Version 1.0
 */
public class Demo4 {
    //此时有问题，因为核心池只有5，栅栏也是五个，而且线程栅栏可复用，那么此时会执行两次
    public static ThreadPoolExecutor t1=new ThreadPoolExecutor(5,10,200, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5);
        for (int i = 0; i < 10; i++) {
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
