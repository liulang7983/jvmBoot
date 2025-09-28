package com.fail.Demo5;

import com.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**模拟的不是很好，纯内存的操作生产时大容量的list，分为几个线程去跑，那么此时会一直GC，反而没有单线程跑的快
 * @Author ming.li
 * @Date 2025/9/24 16:59
 * @Version 1.0
 */
public class DemoFail {
    private static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(4,4,100, TimeUnit.MINUTES,new LinkedBlockingQueue<>());
    public static void main(String[] args) {
        List<User> list=new ArrayList<>();
        List<User> list1=new ArrayList<>();
        for (int i = 0; i <1000 ; i++) {
            list.add(new User("我的位置是:"+i,i));
        }
        for (int i = 0; i <200000 ; i++) {
            list1.add(new User("我的位置是:"+i,i));
        }
        long start = System.currentTimeMillis();
        CountDownLatch downLatch = new CountDownLatch(list.size());
        for (int i = 0; i <list.size() ; i++) {
            final Integer x=list.get(i).getType();
            threadPoolExecutor.execute(()->{
                for (int j = 0; j < list1.size(); j++) {
                    int a=x*list1.get(j).getType()*(x+list1.get(j).getType());
                    int b=x+list1.get(j).getType()+(x+list1.get(j).getType());
                    int c=x-list1.get(j).getType()-(x-list1.get(j).getType());
                    int d=x*list1.get(j).getType()+(x-list1.get(j).getType());
                }
                downLatch.countDown();
            });

        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
