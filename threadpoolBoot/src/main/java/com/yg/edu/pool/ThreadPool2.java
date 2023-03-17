package com.yg.edu.pool;

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
public class ThreadPool2 {

    public static void main(String[] args)throws Exception {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 5000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
    //此时停止其实停不掉，看源码可得，核心池满之前是不判断状态的，只有在添加队列的时候才会怕段状态   ! isRunning(recheck)
        for (int i=0;i<1000;i++){
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("i m task ："+Thread.currentThread().getName());
                }
            },i);
        }

        threadPoolExecutor.shutdown();  //running->shutdown
        System.out.println("我执行了停止");


    }


}
