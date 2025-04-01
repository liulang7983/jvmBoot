package com.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ：杨过
 * @date ：Created in 2020/5/30
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
public class ThreadPool4 {

    public static void main(String[] args)throws Exception {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 1000, 5000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(900));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        //此时停止其实停不掉，看源码可得，核心池满之前是不判断状态的，但是真正加线程的时候是会判断线程池的状态
        //所以所谓的shutdown()后新加入的线程不会执行，但是原有任务会把任务队列里的任务执行完，此时一千个任务全部执行完成
        for (int i=0;i<1000;i++){
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("i m task ："+Thread.currentThread().getName());
                }
            },i);
        }

        threadPoolExecutor.shutdown();  //running->stop
        System.out.println("我执行了停止");


    }


}
