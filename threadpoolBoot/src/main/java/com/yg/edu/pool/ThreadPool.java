package com.yg.edu.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPool {

    public static void main(String[] args)throws Exception {
        /*当任务队列很长时，此时任务可以全部执行
        当任务队列短，且设置为shutdown会丢任务

        */

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 5000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

        for (int i=0;i<6;i++){
            threadPoolExecutor.submit(new MyRunnable(),i);
        }
        System.out.println(threadPoolExecutor.isTerminated());
        threadPoolExecutor.shutdown();  //running->shutdown
        //停止后再添加线程，此时会报错说线程的状态为down
        threadPoolExecutor.submit(new MyRunnable1(),1);
        //threadPoolExecutor.shutdownNow(); //running->stop
        Thread.sleep(1000);
        /*isShutDown：当调用shutdown()或shutdownNow()方法后返回为true。
        isTerminated：当调用shutdown()方法后，并且所有提交的任务完成后返回为true;
        isTerminated：当调用shutdownNow()方法后，成功停止后返回为true;*/
        System.out.println(threadPoolExecutor.isTerminated());


    }

}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("i m task ："+Thread.currentThread().getName());
    }
}

class MyRunnable1 implements Runnable{

    @Override
    public void run() {
        System.out.println("我是停止后添加的任务 ："+Thread.currentThread().getName());
    }
}
