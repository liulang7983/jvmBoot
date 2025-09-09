package com.test;

/**
 * -Xss4m不同的参数会有不同的效果
 * -Xss代表一个线程下总的栈的大小，我这无限递归每次方法都是一个新的栈针
 * 那么当我这个线程下的虚拟机栈大小越大，就代表我能递归的次数越大
 * (正常程序中都不会报java.lang.StackOverflowError，遇到了基本就是无限递归没有出口了)
 * @Author ming.li
 * @Date 2025/9/5 8:49
 * @Version 1.0
 */
public class StackOverflowErrorTest1 {
    static int count = 0;
    static int count1=0;
    static void redoThread() {
        if (count==0){
            new Thread(()->redo1Thread()).start();
        }
        count++;
        redoThread();
    }
    static void redoOne() {
        count++;
        redoOne();
    }

    static void redo() {
        count++;
        redo1();
        redo();
    }
    static void redo1() {
        count1++;
    }
    static void redo1Thread() {
        count1++;
        redo1Thread();
    }

    public static void main(String[] args) {
        try {
            //redoOne();//和下面的方法基本一致，因为redo方法中调用的redo1没有递归，结束后就直接销毁了，没有继续占着虚拟机栈，一直占有的只有redo
            //redo();
            redoThread();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        try {
            Thread.sleep(2000);
            System.out.println("count的值:"+count);
            System.out.println("count1的值:"+count1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
