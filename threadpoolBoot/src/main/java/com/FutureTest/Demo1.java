package com.FutureTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author ming.li
 * @Date 2025/3/14 14:08
 * 多线程通过get(long timeout, TimeUnit unit)，超时放弃并不能释放线程，线程还会执行，只是结果获取不到
 * @Version 1.0
 */
public class Demo1 {
    public static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(1,1,1000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    public static void main(String[] args) {
        List<Future<Integer>> list=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            int a=i;
            Future<Integer> submit = threadPoolExecutor.submit(() -> {
                try {
                    System.out.println("开始处理:"+a);
                    Thread.sleep(5000);
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
            try {
                Integer integer = list.get(i).get(3, TimeUnit.SECONDS);
                System.out.println("答应输出:"+integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println("任务处理完成或者丢弃:"+i);
        }
    }
}
