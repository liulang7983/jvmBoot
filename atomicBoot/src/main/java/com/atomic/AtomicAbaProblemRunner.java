package com.atomic;

import java.util.concurrent.atomic.AtomicInteger;


public class AtomicAbaProblemRunner {
    static AtomicInteger atomicInteger = new AtomicInteger(1);

    /**
     * 此时实际上是出现了ABA问题，在线程main途中，线程other是把atomicInteger从1改到2在改回了1，
     * 然后线程main执行atomicInteger.compareAndSet(a,2)才不会失败
     */

    public static void main(String[] args) {
        Thread main = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = atomicInteger.get();

                System.out.println("操作线程" + Thread.currentThread().getName() + "--修改前操作数值:" + a);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean isCasSuccess = atomicInteger.compareAndSet(a, 2);
                if (isCasSuccess) {
                    System.out.println("主线程操作线程" + Thread.currentThread().getName() + "--Cas修改后操作数值:" + atomicInteger.get());
                } else {
                    System.out.println("主线程CAS修改失败");
                }
            }
        }, "主线程");

        Thread other = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInteger.incrementAndGet();// 1+1 = 2;
                System.out.println("干扰线程操作线程" + Thread.currentThread().getName() + "--increase后值:" + atomicInteger.get());
                //此时如果暂停3秒多则前面会失败
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atomicInteger.decrementAndGet();// atomic-1 = 2-1;
                System.out.println("干扰线程操作线程" + Thread.currentThread().getName() + "--decrease后值:" + atomicInteger.get());
            }
        }, "干扰线程");

        main.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        other.start();

    }
}
