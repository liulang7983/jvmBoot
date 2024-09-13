package com.yg.edu.lockingDequeTest;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2024/9/12 16:20
 * @Version 1.0
 */
public class Demo3 {
    public static ThreadPoolExecutor t1=new ThreadPoolExecutor(10,10,200, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    public static void main(String[] args) {
        LinkedBlockingQueue<Object> objects = new LinkedBlockingQueue<>(20);
        t1.execute(()->test1(objects));
        t1.execute(()->test2(objects));
    }
    public static void test1(LinkedBlockingQueue<Object> objects){
        for (int i = 0; i < 30; i++) {
            try {
                //此时队列满了的话put会阻塞，等待队列空出位置来此时才会继续玩下走
                objects.put(i);
                System.out.println(i+" 插入");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public static void test2(LinkedBlockingQueue<Object> objects){
        while (true){
            Object poll=null;
            try {
                //有值则take获取到值，没有则阻塞直到获取到值
                 poll = objects.take();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("获得值:"+poll);
        }
    }
}
