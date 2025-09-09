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
public class StackOverflowErrorTest {
    static int count = 0;

    static void redo() {
        count++;
        redo();
    }

    public static void main(String[] args) {
        try {
            redo();
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println(count);
        }
    }

}
