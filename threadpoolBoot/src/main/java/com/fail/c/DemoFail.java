package com.fail.c;

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
 * 他也还在执行，导致结果不对
 * @Version 1.0
 */
public class DemoFail {
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 3000, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
    public static void main(String[] args) {
        List<TestBean> list=new ArrayList<>();

        for (int i = 0; i <10 ; i++) {
            list.add(new TestBean(i,-1));
        }
        CountDownLatch downLatch = new CountDownLatch(list.size());
        for (int i = 0; i <list.size() ; i++) {
            TestBean testBean = list.get(i);
            threadPoolExecutor.execute(()->test1(testBean,list,downLatch));
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<TestBean> collect = list.stream().filter(o -> o.getStart().equals(o.getEnd())).collect(Collectors.toList());
        if (collect.size()!=list.size()){
            //有为start的
            System.out.println("任务失败");
        }else {
            System.out.println("任务成功");
        }
    }
    public static void test1(TestBean testBean,List<TestBean> list,CountDownLatch downLatch){
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
            List<TestBean> collect = list.stream().filter(o -> o.getEnd().equals(6)).collect(Collectors.toList());
            //任务6不是true
            if (collect.size()==0){
                testBean.setEnd(-1);
            }
        }
        downLatch.countDown();
    }
}
