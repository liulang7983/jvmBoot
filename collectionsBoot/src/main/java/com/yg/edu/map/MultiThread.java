package com.yg.edu.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class MultiThread implements Runnable {
    private static Map<Integer,Integer> map = new ConcurrentHashMap<>(11);

    private static AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void run() {
        while(atomicInteger.get() < 1000000){
            map.put(atomicInteger.get(),atomicInteger.get());
            atomicInteger.incrementAndGet();
        }
    }
}
