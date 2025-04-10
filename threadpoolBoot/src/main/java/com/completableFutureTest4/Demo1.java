package com.completableFutureTest4;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ming.li
 * @Date 2025/4/10 11:05
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Map map=new HashMap();
        test1(map);
        test2(map);
        test3(map);
        test4(map);
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
        System.out.println(map);
    }

    public static void test1(Map map){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put("test1","test1");
    }
    public static void test2(Map map){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put("test2","test2");
    }
    public static void test3(Map map){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put("test3","test3");
    }
    public static void test4(Map map){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put("test4","test4");
    }

}
