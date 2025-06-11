package com.fail.demo4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author ming.li
 * @Date 2025/4/2 11:09
 * 存在线程依赖，被依赖的线程哪怕放在前面执行，也可能出现他一个任务一直在执行很久，导致依赖他的线程执行完了
 * 所以我们分开来，被依赖的线程执行完成后再执行依赖的线程，那就肯定不会又错了
 * @Version 1.0
 */
public class DemoSucceed {
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 3000, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
    public static void main(String[] args) {
        List<com.fail.c.TestBean> list=new ArrayList<>();

        for (int i = 0; i <10 ; i++) {
            list.add(new com.fail.c.TestBean(i,-1));
        }
        List<com.fail.c.TestBean> collect1 = list.stream().filter(o -> o.getStart().equals(6)).collect(Collectors.toList());
        List<com.fail.c.TestBean> collect2 = list.stream().filter(o -> !o.getStart().equals(6)).collect(Collectors.toList());
        CountDownLatch downLatch1 = new CountDownLatch(collect1.size());
        for (int i = 0; i <collect1.size() ; i++) {
            com.fail.c.TestBean testBean = collect1.get(i);
            threadPoolExecutor.execute(()->test1(testBean,list,downLatch1));
        }
        try {
            downLatch1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CountDownLatch downLatch2 = new CountDownLatch(collect2.size());
        for (int i = 0; i <collect2.size() ; i++) {
            com.fail.c.TestBean testBean = collect2.get(i);
            threadPoolExecutor.execute(()->test1(testBean,list,downLatch2));
        }
        try {
            downLatch2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<com.fail.c.TestBean> collect = list.stream().filter(o -> o.getStart().equals(o.getEnd())).collect(Collectors.toList());
        if (collect.size()!=list.size()){
            //有为start的
            System.out.println("任务失败");
        }else {
            System.out.println("任务成功");
        }
    }
    public static void test1(com.fail.c.TestBean testBean, List<com.fail.c.TestBean> list, CountDownLatch downLatch){
        if (testBean.getStart().equals(6)){
            //模拟任务6执行很久
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        testBean.setEnd(testBean.getStart());
        if (testBean.getStart().equals(8)){
            //任务8依赖任务6的执行结果,此时需要6的 result是true
            List<com.fail.c.TestBean> collect = list.stream().filter(o -> o.getEnd().equals(6)).collect(Collectors.toList());
            //任务6不是true
            if (collect.size()==0){
                testBean.setEnd(-1);
            }
        }
        downLatch.countDown();
    }
}
