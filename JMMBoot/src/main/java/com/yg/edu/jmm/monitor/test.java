package com.yg.edu.jmm.monitor;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author ming.li
 * @date 2023/11/14 17:40
 */
public class test {
    //无锁
    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }
}
