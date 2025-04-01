package com;

import org.junit.Test;

/**
 * @author ming.li
 * @date 2023/5/16 19:46
 */
public class SystemDemo {

    private volatile static int counter=0;
    private static Object o=new Object();
    //此时不加锁不加volatile修饰，十个线程那么到不了10000(哪怕加了volatile也到不了10000，因为volatile不保证原子性)
    @Test
    public void test1(){
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter++;
                }
            });
            thread.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter);
    }
    //此时不加锁不加volatile也可以到10000，是因为println里面是加锁的(有误)
    @Test
    public void test2(){
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j <1000; j++) {
                    System.out.println( counter++);;
                }
            });
            thread.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter);
    }

}
