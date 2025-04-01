package com.ThreadFactoryTest;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ming.li
 * @Date 2025/2/25 14:33
 * 使用自定义的ThreadFactory发现问题会更方便定位，当然其实在县城里加日志也比较好定位
 * @Version 1.0
 */
public class ThreadFactoryDemo1 {
    public static ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(4,4,1000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(),new NameThreadFactory());
    public static void main(String[] args) {
        for (int i = 0; i < 90; i++) {
            int a=i;
            poolExecutor.execute(()->{
                System.out.println(Thread.currentThread().getName()+" 我是第:"+a+"个");
            });
        }
    }

}
