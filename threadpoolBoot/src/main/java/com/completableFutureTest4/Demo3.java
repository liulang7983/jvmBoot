package com.completableFutureTest4;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author ming.li
 * @Date 2025/4/10 11:05
 * 分别异步处理，然后再等到全部执行完再往下走，所以时间是差不多等于执行时间最久的那个
 * @Version 1.0
 */
public class Demo3 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Map map=new HashMap();
        CompletableFuture<Void> test1 = CompletableFuture.runAsync(() -> test1(map));
        CompletableFuture<Void> test2 = CompletableFuture.runAsync(() -> test2(map));
        CompletableFuture<Void> test3 = CompletableFuture.runAsync(() -> test3(map));
        CompletableFuture<Void> test4 = CompletableFuture.runAsync(() -> test4(map));
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(test1, test2,test3,test4);
        try {
            allTasks.get();
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
