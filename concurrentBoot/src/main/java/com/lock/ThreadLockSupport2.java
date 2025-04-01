package com.lock;

import java.util.concurrent.locks.LockSupport;

public class ThreadLockSupport2 {
    /**
     此时 LockSupport.park()会线程卡住
     LockSupport.unpark(t0)具体的线程会唤醒线程
     但是当t0.interrupt()会清除所有的线程卡主的标记
     注意：LockSupport.park()有两个方法，不带参数是后续都不在阻塞，带参数就是放开一次
     */

    public static void main(String[] args) {

        Thread t0 = new Thread(new Runnable() {

            @Override
            public void run() {
                Thread current = Thread.currentThread();
                System.out.println("{},开始执行!"+current.getName());
                for(;;){//spin 自旋
                    System.out.println("准备park住当前线程：{}...."+current.getName());
                    LockSupport.park();

                    //这一行是判断当前线程是否中断阻塞，如果是则卡住
                    Thread.interrupted();

                    System.out.println("当前线程{}已经被唤醒...."+current.getName());
                }
            }

        },"t0");

        t0.start();

        try {
            Thread.sleep(5000);
            System.out.println("准备唤醒{}线程!"+t0.getName());
            //LockSupport.unpark(t0);
            t0.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

