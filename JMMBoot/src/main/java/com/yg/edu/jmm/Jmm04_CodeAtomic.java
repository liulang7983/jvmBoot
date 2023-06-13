package com.yg.edu.jmm;

import com.sun.xml.internal.ws.developer.MemberSubmissionAddressingFeature;

import javax.security.sasl.SaslServer;


public class Jmm04_CodeAtomic {
    /**
     此时的值不一定是10000，因为counter++分三步- 读，自加，写回，此时分为10个线程，volatile不保证原子性
     */

    private volatile static int counter = 0;
    static Object object = new Object();

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                        counter++;//分三步- 读，自加，写回
                }
            });
            thread.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter);

    }
}
