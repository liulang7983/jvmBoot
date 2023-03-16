package com.yg.edu.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;


@Slf4j
public class Juc01_Thread_LockSupport {
    /**
     此时 LockSupport.park()会线程卡住
     LockSupport.unpark(t0)具体的线程会唤醒线程
     但是当t0.interrupt()会清除所有的线程卡主的标记
     */

    public static void main(String[] args) {

        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread current = Thread.currentThread();
                log.info("{},开始执行!",current.getName());
                for(;;){//spin 自旋
                    log.info("准备park住当前线程：{}....",current.getName());
                    LockSupport.park();
                    System.out.println(Thread.interrupted());
                    log.info("当前线程{}已经被唤醒....",current.getName());

                }
            }
        },"t0");

        t0.start();

        try {
            Thread.sleep(2000);
            log.info("准备唤醒{}线程!",t0.getName());
            LockSupport.unpark(t0);
            Thread.sleep(2000);
            t0.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
