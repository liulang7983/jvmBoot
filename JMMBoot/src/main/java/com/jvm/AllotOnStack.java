package com.jvm;

import com.bean.User;

/**
 * @Author ming.li
 * @Date 2025/2/8 9:59
 * @Version 1.0
 * 不gc(开启了逃逸分析,User只在方法里被调用，分配在栈上，随着栈销毁而销毁)
 *  -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 *  gc(关闭了逃逸分析,分配在堆上，容积不够一直gc)
 *  -Xmx15m -Xms15m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 *  gc(虽然开启了逃逸分析，但是关闭了标量替换，逃逸分析主要依赖标量替换分配在栈上，此时关闭了标量替换则分配在堆上)
 *  -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 */
public class AllotOnStack {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void alloc() {
        User user = new User();
        user.setId(1);
        user.setName("zhuge");
    }
}
