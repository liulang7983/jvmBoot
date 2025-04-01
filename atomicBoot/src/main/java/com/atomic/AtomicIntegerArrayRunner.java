package com.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;


public class AtomicIntegerArrayRunner {

    static int[] value = new int[]{1,2};
    static AtomicIntegerArray aiArray = new AtomicIntegerArray(value);
    //处理后此时aiArray的下标0变化了，但是value的值没有变化

    public static void main(String[] args) {
        //todo 原子修改数组下标0的数值
        aiArray.getAndSet(0,3);
        System.out.println(aiArray.get(0));
        System.out.println(value[0]);
}

}
