package com.yg.edu.jmm.monitor;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author ming.li
 * @date 2023/11/14 17:40
 */
public class test2 {
    //jvm会延迟启动偏向锁(大概4秒)，好像是jvm启动的时候自己会启动蛮多东西，可能会照成竞争，为何减少锁升级的开销，偏向锁延迟启动
    //偏向锁
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        synchronized (object){
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }

}
