package com.threadTest;

/**
 * @author ming.li
 * @date 2023/6/29 9:50
 */
public class Demo1 {
    static class BThread extends Thread {
        public BThread() {
            super("[BThread] Thread");
        }

        ;

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " start.");
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println(threadName + " loop at " + i);
                    Thread.sleep(1000);
                }
                System.out.println(threadName + " end.");
            } catch (Exception e) {
                System.out.println("Exception from " + threadName + ".run");
            }
        }
    }

    static class AThread extends Thread {
        BThread bt;

        public AThread(BThread bt) {
            super("[AThread] Thread");
            this.bt = bt;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " start.");
            try {
                //等待bt执行完继续往下走
                bt.join();
                System.out.println(threadName + " end.");
            } catch (Exception e) {
                System.out.println("Exception from " + threadName + ".run");
            }
        }
    }

    public static class TestDemo {
        public static void main(String[] args) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " start");
            BThread bt = new BThread();
            AThread at = new AThread(bt);
            try {
                bt.start();
                Thread.sleep(2000);
                at.start();
                //此处不等待执行则主线程会直接走到后面打印 main end!
                //主线程等待at执行完再继续往下走
                at.join();
            } catch (Exception e) {
                System.out.println("Exception from main");
            }
            System.out.println(threadName + " end!");
        }
    }
}
