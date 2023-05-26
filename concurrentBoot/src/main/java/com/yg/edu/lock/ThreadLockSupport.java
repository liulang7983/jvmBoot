package com.yg.edu.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;


@Slf4j
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
            log.info("准备清除标记{}",t0.getName());
            //标记清除,标记清除了那此时就会自动unpark一次但是后续也无法再次unpark
            t0.interrupt();
            Thread.sleep(2000);
            log.info("清除标记了,再次unpark{}",t0.getName());
            //标记清除了那此时就会自动unpark一次但是后续也无法再次unpark
            //LockSupport.unpark(t0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
