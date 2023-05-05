package com.jdkTest;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author ming.li
 * @date 2023/4/26 13:49
 */
public class Test2 {
    //偏向锁测试
    public static void main(String[] args)throws Exception {
        TimeUnit.SECONDS.sleep(5);
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        synchronized (object){
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }
}
/*注:jvm会延迟启动偏向锁(大概4秒)，好像是jvm启动的时候自己会启动蛮多东西，可能会照成竞争，为何减少锁升级的开销，偏向锁延迟启动
   加入synchronized延迟五秒打印
开启偏向锁
无锁状态(匿名偏向,可偏向状态)
00000101 00000000 00000000 00000000
有同步块
00000101 01000000 00100001 00000011
此时没加锁和加锁的o都是101偏向锁，但是没加锁的前23位线程ID是没有的，此时就是预备加锁，哪个线程要自己来拿
*/
