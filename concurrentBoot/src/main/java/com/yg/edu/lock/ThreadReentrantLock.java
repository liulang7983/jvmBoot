package com.yg.edu.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ThreadReentrantLock {
    //true是公平锁，false是非公平锁，默认是非公平锁

    private static ReentrantLock lock = new ReentrantLock(true);

    public static void reentrantLock(){
        String threadName = Thread.currentThread().getName();
        log.info("Thread:{},进入了方法",threadName);
        //默认创建的是独占锁，排它锁；同一时刻读或者写只允许一个线程获取锁
        lock.lock();
        log.info("Thread:{},第一次加锁",threadName);
        log.info("获得state：{}");
            lock.lock();
            log.info("Thread:{},第二次加锁",threadName);
            lock.unlock();
            boolean flag = true;
            while(true){
                if(!flag){
                    break;
                }
            }
            log.info("Thread:{},第一次解锁",threadName);
        lock.unlock();
        log.info("Thread:{},第二次解锁",threadName);
    }

    public static void reen2(){
        lock.lock();
        //dsssxxxxxx逻辑
        lock.unlock();
    }

    public static void main(String[] args) {
        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock();
            }
        },"t0");

        t0.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock();
            }
        },"t1");

        t1.start();
    }

}
