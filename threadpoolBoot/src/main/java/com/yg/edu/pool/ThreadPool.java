package com.yg.edu.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ：杨过
 * @date ：Created in 2020/5/30
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
public class ThreadPool {

    public static void main(String[] args)throws Exception {

        /*for (int i=0;i<300;i++){
            new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            }).start();
        }*/
        //Executors.newCachedThreadPool();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 5000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

        for (int i=0;i<6;i++){
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("i m task ："+Thread.currentThread().getName());
                }
            },i);
        }
        System.out.println(threadPoolExecutor.isTerminated());
        //threadPoolExecutor.shutdown();  //running->shutdown
        //threadPoolExecutor.shutdownNow(); //running->stop
        Thread.sleep(1000);
        /*isShutDown：当调用shutdown()或shutdownNow()方法后返回为true。
        isTerminated：当调用shutdown()方法后，并且所有提交的任务完成后返回为true;
        isTerminated：当调用shutdownNow()方法后，成功停止后返回为true;*/
        System.out.println(threadPoolExecutor.isTerminated());


    }


}
