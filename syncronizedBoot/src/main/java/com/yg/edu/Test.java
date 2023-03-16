package com.yg.edu;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author ming.li
 * @date 2023/3/16 15:27
 */
public class Test {
    public static void main(String[] args)throws Exception {
        Thread.sleep(5000);
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }
}
/**
 此时需要关闭默认开启偏向锁 -XX:-UseBiasedLocking
 java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
 12     4        (loss due to the next object alignment)
 Instance size: 16 bytes
 Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

 由于操作系统：大端模式，小端模式
 此时小端模式
 00000001 00000000 00000000 00000000
 后面的需要往前放(按照八位八位的往前)
 00000000 00000000 00000000 00000001
 最后是001是无锁，此时前25位hashCode是没有的
 001无锁
 101偏向锁
  00轻量级锁
  10重量级锁
 */