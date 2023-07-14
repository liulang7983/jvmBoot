package com.yg.edu.pool;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author ming.li
 * @date 2023/7/14 19:49
 */
public class DequeTest {
    public static void main(String[] args) {
        //此时t2会阻塞
        LinkedBlockingDeque deque = new LinkedBlockingDeque<String>();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    deque.add("张三");
                    System.out.println("写入了");
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    Object take = null;
                    try {
                        take = deque.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(take);
                }
            }
        });
        t1.start();
        t2.start();

    }
}
