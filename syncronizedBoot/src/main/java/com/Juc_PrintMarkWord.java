package com;

import org.openjdk.jol.info.ClassLayout;


public class Juc_PrintMarkWord {
    /**
     关闭默认开启的偏向锁:-XX:-UseBiasedLocking
     001无锁
     101偏向锁
      00轻量级锁
      10重量级锁
     */

    public static void main(String[] args) throws InterruptedException {
        // 需要sleep一段时间，因为java对于偏向锁的启动是在启动几秒之后才激活。
        // 因为jvm启动的过程中会有大量的同步块，且这些同步块都有竞争，如果一启动就启动
        // 偏向锁，会出现很多没有必要的锁撤销
        Thread.sleep(10000);
        T t = new T();
        //未出现任何获取锁的时候
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
        /**
         T object internals:
         OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         8     4        (object header)                           43 c1 00 20 (01000011 11000001 00000000 00100000) (536920387)
         12     4    int T.i                                       0
         Instance size: 16 bytes
         Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         由于操作系统：大端模式，小端模式
         此时小端模式
         00000001 00000000 00000000 00000000
         后面的需要往前放(按照八位八位的往前)
         00000000 00000000 00000000 00000001
         最后是001是无锁，此时前25位hashCode是没有的
         */
        System.out.println("==========================================");
        synchronized (t){
            // 获取一次锁之后
            System.out.println(ClassLayout.parseInstance(t).toPrintable());
        }
        // 输出hashcode
        System.out.println(t.hashCode());
        // 计算了hashcode之后，将导致锁的升级
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
        synchronized (t){
            // 再次获取锁
            System.out.println(ClassLayout.parseInstance(t).toPrintable());
        }
    }
}

class T{
    int i = 0;
}