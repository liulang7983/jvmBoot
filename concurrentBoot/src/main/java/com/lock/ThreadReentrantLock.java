package com.lock;

import java.util.concurrent.locks.ReentrantLock;


public class ThreadReentrantLock {
    //true是公平锁，false是非公平锁，默认是非公平锁

    private static ReentrantLock lock = new ReentrantLock(true);

    public static void reentrantLock(){
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread:{},进入了方法"+threadName);
        //默认创建的是独占锁，排它锁；同一时刻读或者写只允许一个线程获取锁
        lock.lock();
        System.out.println("Thread:{},第一次加锁"+threadName);
            lock.lock();
        System.out.println("Thread:{},第二次加锁"+threadName);
            lock.unlock();
            boolean flag = true;
            while(true){
                if(!flag){
                    break;
                }
            }
        System.out.println("Thread:{},第一次解锁"+threadName);
        lock.unlock();
        System.out.println("Thread:{},第二次解锁"+threadName);
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
