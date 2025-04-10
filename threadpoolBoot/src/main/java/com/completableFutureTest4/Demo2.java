package com.completableFutureTest4;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author ming.li
 * @Date 2025/4/10 11:05
 * 异步处理，但是在一个异步CompletableFuture后面接thenRunAsync好像是链路调用，没有实现异步调用
 * @Version 1.0
 */
public class Demo2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Map map=new HashMap();
        CompletableFuture<Void> test1 = CompletableFuture.runAsync(() -> test1(map)).thenRunAsync(() -> test2(map)).thenRunAsync(() -> test3(map)).thenRunAsync(() -> test4(map));
        try {
            test1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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
