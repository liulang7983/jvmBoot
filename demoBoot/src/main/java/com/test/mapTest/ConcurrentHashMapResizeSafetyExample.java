package com.test.mapTest;

/**
 * @Author ming.li
 * @Date 2025/2/24 16:07
 * @Version 1.0
 */
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapResizeSafetyExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>(2);

        // 模拟多线程插入元素触发扩容
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put("key" + i, i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                map.put("key" + i, i);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final size: " + map.size());
        ConcurrentHashMap.KeySetView<String, Integer> strings = map.keySet();
        for (String s:strings){
            System.out.println(map.get(s));
        }
    }
}