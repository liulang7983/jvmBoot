package com.yg.edu.tools.countdown;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class CountDownLaunchRunner {

    static int sub = 0;
    static Object object = new Object();
    //实现公平队列，此时只有所有线程都就绪的时候才会继续往下走

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        /*new Thread(new SeeDoctorTask(countDownLatch)).start();
        new Thread(new QueueTask(countDownLatch)).start();*/


        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("我是线程"+Thread.currentThread().getName()+",我在这等着");
                        //所有线程在此等待，一直到全部放入完成，公平
                        countDownLatch.await();
                        System.out.println("我是线程"+Thread.currentThread().getName()+",我进来了");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (object){
                        for(int j=0;j<1000;j++){
                            sub++;
                        }
                    }
                }
            }, String.valueOf(i)).start();
            Thread.sleep(200);
            //等待线程池中的2个任务执行完毕，否则一直等待,zk分布式锁
            countDownLatch.countDown();
        }

        System.out.println("over，回家 cost:"+(System.currentTimeMillis()-now));
        Thread.sleep(2000);
        System.out.println("sub的值:"+sub);
    }

}
