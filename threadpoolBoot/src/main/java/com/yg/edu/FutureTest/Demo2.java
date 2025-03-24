package com.yg.edu.FutureTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author ming.li
 * @Date 2025/3/14 14:08
 * 多线程通过get(long timeout, TimeUnit unit)，超时放弃并不能释放线程死锁
 * @Version 1.0
 */
public class Demo2 {
    public static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(3,3,1000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    public static void main(String[] args) {
        List<Future<Integer>> list=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            int a=i;
            Future<Integer> submit = threadPoolExecutor.submit(() -> {
                try {
                    System.out.println("开始处理:"+a);
                    Thread.sleep(300000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("处理完成:"+a);
                return a;
            });
            list.add(submit);
        }
        System.out.println("任务创建完成");
        for (int i = 0; i < list.size(); i++) {
            Future<Integer> integerFuture = list.get(i);
            try {
                Integer integer = integerFuture.get(10, TimeUnit.SECONDS);
                System.out.println("答应输出:"+integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                integerFuture.cancel(true);
                e.printStackTrace();
            }
            System.out.println("任务处理完成或者丢弃:"+i);
        }
    }
}
