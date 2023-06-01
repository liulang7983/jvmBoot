package com.yg.edu.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ming.li
 * @date 2023/5/15 20:36
 */
public class CallerRunsPolicyPoo1 {
    public static void main(String[] args)throws Exception {
        /*
        CallerRunsPolicy代表放不下的任务我自己调用run执行
        那么此时就其实就是两个任务在执行，一个是线程池的一个核心池，一个是main
        */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 5000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1),new ThreadPoolExecutor.CallerRunsPolicy());
        long start = System.currentTimeMillis();
        for (int i=0;i<100;i++){
            threadPoolExecutor.submit(new CallerRunsPolicyRunnable(),i);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
    }

}

class CallerRunsPolicyRunnable implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("i m task ："+Thread.currentThread().getName());
    }
}
