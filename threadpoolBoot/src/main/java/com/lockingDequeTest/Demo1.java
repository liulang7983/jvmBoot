package com.lockingDequeTest;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/9/12 16:20
 * @Version 1.0
 */
public class Demo1 {
    public static ThreadPoolExecutor t1=new ThreadPoolExecutor(10,10,200, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    public static void main(String[] args) {
        LinkedBlockingQueue<Object> objects = new LinkedBlockingQueue<>(20);
        t1.execute(()->test1(objects));
        t1.execute(()->test2(objects));
    }
    public static void test1(LinkedBlockingQueue<Object> objects){
        for (int i = 0; i < 10; i++) {
            boolean offer = objects.offer(i);
            System.out.println(i+" 插入结果:"+offer);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public static void test2(LinkedBlockingQueue<Object> objects){
        while (true){
            //有值则poll获取到值，没有则为空
            Object poll = objects.poll();
            System.out.println("获得值:"+poll);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
