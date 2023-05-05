package com.jdkTest;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author ming.li
 * @date 2023/4/26 13:49
 */
public class Test1 {

    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }
   /* 无锁测试
    由于操作系统：大端模式，小端模式
            此时小端模式
00000001 00000000 00000000 00000000
    后面的需要往前放(按照八位八位的往前)
00000000 00000000 00000000 00000001
    最后是001是无锁，此时前25位hashCode是没有的*/
}
