package com.jmm.monitor;

import org.openjdk.jol.info.ClassLayout;


/**
 * @author ming.li
 * @date 2023/11/14 17:40
 */
public class test4 {
    //重量级锁
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        Object a = new Object();

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    System.out.println("thread1 locking");
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
                    try {
                        //让线程晚点儿死亡，造成锁的竞争
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                synchronized (a){
                    System.out.println("thread2 locking");
                    System.out.println(ClassLayout.parseInstance(a).toPrintable());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread1.start();
        thread2.start();
    }

}
