package com.test;

/**
 * @Author ming.li
 * @Date 2025/9/8 14:56
 * @Version 1.0
 */
public class CpuHighTest {
    public static final int initData = 666;
    //public static User user = new User();


    public int compute() { //一个方法对应一块栈帧内存区域
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        CpuHighTest cpuHighTest = new CpuHighTest();
        while (true){
            cpuHighTest.compute();
        }

    }

}
