package com.yg.edu.jmm.monitor;

import org.openjdk.jol.info.ClassLayout;


/**
 * @author ming.li
 * @date 2023/11/14 17:40
 */
public class test3 {
    //偏向锁
    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        new Thread(()->{
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        new Thread(()->{
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();
    }


}
