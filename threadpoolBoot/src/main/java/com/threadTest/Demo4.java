package com.threadTest;

/**
 * @Author ming.li
 * @Date 2024/8/21 9:18
 * @Version 1.0
 */
public class Demo4 {
    public static void main(String[] args) {
        yieldDemo ms = new yieldDemo();
        Thread t1 = new Thread(ms,"张三吃完还剩");
        Thread t2 = new Thread(ms,"李四吃完还剩");
        Thread t3 = new Thread(ms,"王五吃完还剩");
        t1.start();
        t2.start();
        t3.start();
    }
}
class yieldDemo implements Runnable{
    int count = 20;
    @Override
    public void run() {
        while (true) {
            if(count>0){
                System.out.println(Thread.currentThread().getName() + count-- + "个瓜");
                if(count % 2 == 0){
                    Thread.yield();//线程让步
                }
            }
        }
    }
}
