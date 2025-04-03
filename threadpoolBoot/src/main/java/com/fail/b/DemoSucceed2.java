
package com.fail.b;

import java.util.concurrent.*;

/**
 * @Author ming.li
 * @Date 2025/4/2 10:42
 * 此时test1使用的线程池是核心池只有1，那么此时test1里面调用的test2出现了死锁，导致CountDownLatch一直阻塞
 * 那么我们可以让test2可能死锁的地方加上线程池超时的内判定，超过多少时间直接放弃跳过，这样CountDownLatch就不会一直阻塞
 * 其实这里有问题当多个任务超时响应的时候，可以跑完的任务可能全部被卡主的任务给整超时了，误判率很高，b1解决
 * @Version 1.0
 */
public class DemoSucceed2 {
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 3000, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
    public static ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(3, 3, 3000, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
    public static ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(1, 1, 3000, TimeUnit.MINUTES, new LinkedBlockingDeque<>());

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> test1(finalI));
        }
    }
    public static void test1(Integer a) {
        System.out.println("test1开始:"+a);
        CountDownLatch downLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            try {
                //数据准备时间
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int finalI = i;
            threadPoolExecutor1.execute(() -> test2(downLatch, finalI,a));
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test1完成=============:"+a);
    }

    public static void test2(CountDownLatch downLatch, Integer x,Integer a) {
        Future<?> submit = threadPoolExecutor2.submit(() -> {
            if (x.equals(1) && a.equals(1)) {
                try {
                    //某些情况下死锁
                    Thread.sleep(4000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            Object o = submit.get(12,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            //超时了直接丢弃
            submit.cancel(true);
        }

        System.out.println("test2完成：" + x);
        downLatch.countDown();
    }
}