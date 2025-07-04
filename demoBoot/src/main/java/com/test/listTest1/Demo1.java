package com.test.listTest1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2025/6/27 15:04
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 200000; i++) {
            list.add(i);
        }
        iterator1(list);
        iterator2(list);//1和2时间差不多
        iterator3(list);//超级快
    }
    public static void iterator1(List<Integer> list){
        long start = System.currentTimeMillis();
        for (int i = 0; i <list.size() ; i++) {
            list.get(i);
        }
        System.out.println();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
    public static void iterator2(List<Integer> list){
        long start = System.currentTimeMillis();
        for (int i:list) {
            list.get(i);
        }
        System.out.println();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
    public static void iterator3(List<Integer> list){
        long start = System.currentTimeMillis();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            iterator.next();
        }
        System.out.println();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
