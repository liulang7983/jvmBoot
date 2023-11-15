package com.yg.edu.jmm.monitor;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author ming.li
 * @date 2023/11/14 17:40
 */
public class test1 {
    //正常应该是偏向锁，但此时是轻量级锁
    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        synchronized (object){
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }

}
