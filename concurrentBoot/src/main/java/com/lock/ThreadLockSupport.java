package com.lock;

import java.util.concurrent.locks.LockSupport;


public class ThreadLockSupport {
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
                System.out.println(current.getName()+"开始执行!");
                for(;;){//spin 自旋
                    System.out.println("准备park住当前线程："+current.getName());
                    LockSupport.park();
                    //屏蔽这一行代码就会一直循环，不屏蔽就会跑两次就卡住为啥？
                    System.out.println(Thread.interrupted());

                    System.out.println("当前线程已经被唤醒："+current.getName());

                }
            }
        },"t0");

        t0.start();

        try {
            Thread.sleep(2000);
            System.out.println("准备唤醒线程:"+t0.getName());
            LockSupport.unpark(t0);
            Thread.sleep(2000);
            System.out.println("准备清除标记:"+t0.getName());
            //标记清除,标记清除了那此时就会自动unpark一次但是后续也无法再次unpark
            t0.interrupt();
            Thread.sleep(2000);
            System.out.println("清除标记了,再次unpark:"+t0.getName());
            //标记清除了那此时就会自动unpark一次但是后续也无法再次unpark
            //LockSupport.unpark(t0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
