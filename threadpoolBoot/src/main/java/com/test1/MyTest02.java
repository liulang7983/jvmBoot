package com.test1;

/**
 * @Author ming.li
 * @Date 2025/1/20 17:20
 * @Version 1.0
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/*
    练习Executors获取ExecutorService,然后调用方法,提交任务;
 */
public class MyTest02 {
    public static void main(String[] args) {
        test1();
        test2();
    }
    //练习方法newFixedThreadPool
    private static void test1() {
        //1:使用工厂类获取线程池对象
        ExecutorService es = Executors.newFixedThreadPool(3);
        //2:提交任务;
        for (int i = 1; i <=10 ; i++) {
            es.submit(new MyRunnable2(i));
        }
    }
    private static void test2() {
        //1:使用工厂类获取线程池对象
        ExecutorService es = Executors.newFixedThreadPool(3,new ThreadFactory() {
            int n=1;
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"自定义的线程名称"+n++);
            }
        });
        //2:提交任务;
        for (int i = 1; i <=10 ; i++) {
            es.submit(new MyRunnable2(i));
        }
    }
}

/*
    任务类,包含一个任务编号,在任务中,打印出是哪一个线程正在执行任务
 */
class MyRunnable2 implements Runnable{
    private  int id;
    public MyRunnable2(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        //获取线程的名称,打印一句话
        String name = Thread.currentThread().getName();
        System.out.println(name+"  执行了任务..."+id);
    }
}

