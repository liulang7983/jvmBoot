package com.lock;


import java.util.concurrent.locks.ReentrantLock;

public class ThreadInterrupt {

    private static ReentrantLock lock = new ReentrantLock(true);

    public static void reentrantLock() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        boolean flag = false;
        lock.lockInterruptibly();
        System.out.println("Thread:{},加锁成功!"+threadName);
            while(true){
                if(Thread.interrupted()){
                    break;
                }
                //逻辑,批处理数据
                //逻辑
            }
        lock.unlock();
        System.out.println("Thread:{},锁退出同步块"+threadName);
    }

    public static void main(String[] args) {
        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                try {
                    reentrantLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //异常处理
                }
            }
        },"t1");
        t1.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt();

    }

}
