package com.whileThread;

/**
 * @Author ming.li
 * @Date 2025/7/21 13:49
 * @Version 1.0
 */
class TicketSystem implements Runnable {
    private int tickets = 10;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 同步代码块，保证同一时刻只有一个线程执行
            synchronized (this) {
                if (tickets <= 0) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " 卖出票号: " + tickets);
                System.out.println(Thread.currentThread().getName() + " 卖出时间:"+System.currentTimeMillis());
                tickets--;
            }
        }
    }
}

public class TicketExample {
    public static void main(String[] args) {
        TicketSystem ticketSystem = new TicketSystem();
        System.out.println("开始时间:"+System.currentTimeMillis());
        Thread t1 = new Thread(ticketSystem, "窗口1");
        Thread t2 = new Thread(ticketSystem, "窗口2");

        t1.start();
        t2.start();
    }
}
