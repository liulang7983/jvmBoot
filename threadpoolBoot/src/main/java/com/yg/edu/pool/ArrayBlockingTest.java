package com.yg.edu.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ming.li
 * @date 2023/7/17 19:42
 */
public class ArrayBlockingTest {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(2,4,2000, TimeUnit.SECONDS,new ArrayBlockingQueue<>(20,true),new RejectedExecutionTest1());
        for (int i = 0; i < 200; i++) {
            poolExecutor.execute(new ArrayBlockingTest1(i));
        }

    }
}

class RejectedExecutionTest1 implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        ArrayBlockingTest1 r1 = (ArrayBlockingTest1) r;
        System.out.println("我是:"+r1.getIndex()+"我线程和队列爆满，写入数据库");
    }
}

class ArrayBlockingTest1 implements Runnable{
    private Integer index;

    public ArrayBlockingTest1(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println("我是:"+index);
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
