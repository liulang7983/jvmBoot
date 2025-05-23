package com.tools;

import java.util.concurrent.Exchanger;


public class ExchangerRunner {

    public static void main(String []args) {

        final Exchanger<Integer> exchanger = new Exchanger<Integer>();
        for(int i = 0 ; i < 10 ; i++) {
            final Integer num = i;
            new Thread() {
                @Override
                public void run() {
                    System.out.println("我是线程：Thread_" + this.getName() + "我的数据是：" + num);
                    try {
                        Integer exchangeNum = exchanger.exchange(num);
                        Thread.sleep(1000);
                        System.out.println("我是线程：Thread_" + this.getName() + "我原先的数据为：" + num + " , 交换后的数据为：" + exchangeNum);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

}
