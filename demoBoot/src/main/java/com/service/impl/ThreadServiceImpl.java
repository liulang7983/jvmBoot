package com.service.impl;

import com.bean.ApiResult;
import com.service.ThreadService;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author ming.li
 * @Date 2024/9/2 10:17
 * @Version 1.0
 */
@Service
public class ThreadServiceImpl implements ThreadService {
    public static ThreadPoolExecutor executor=new ThreadPoolExecutor(3,3,1000, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
    public static ReentrantLock lock=new ReentrantLock();

    @Override
    public ApiResult getThread() {
        for (int i = 0; i < 8; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int x=i;
            executor.execute(()->getUser(x));
        }
        return null;
    }
    public String getUser(Integer i){
        System.out.println("进来了:"+i);
        long start = System.currentTimeMillis();
        System.out.println("hash值:"+lock.hashCode());
        System.out.println("hash值:"+lock.isLocked());
        lock.lock();
        long end = System.currentTimeMillis();
        System.out.println("加锁成功:"+i);
        System.out.println("加锁耗时:"+(end-start));
        try {
            System.out.println("结束:"+i);
            return "x";
        } finally {
            Thread thread = new Thread(new MyRunable(lock));
            thread.start();
        }

    }
}
class MyRunable implements Runnable{
    public ReentrantLock lock;
    public MyRunable(ReentrantLock lock){
        this.lock=lock;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("释放锁");
            System.out.println("hash值:"+lock.hashCode());
            lock.unlock();
        }

    }
}
