package com.test.listTest1;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2025/8/20 14:15
 * @Version 1.0
 */
public class Demo2 {
    //时间竟然差不多
    public static void main(String[] args) {
        List<Integer> list1=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list1.add(i);
        }
        for (int i = 0; i < 100000; i++) {
            list2.add(i);
        }
        long l = System.currentTimeMillis();
        System.out.println(bigToLittle(list1,list2));
        long l1 = System.currentTimeMillis();
        System.out.println("大的循环里面循环小的:"+(l1-l));

        l = System.currentTimeMillis();
        System.out.println(littleToBig(list1,list2));
        l1 = System.currentTimeMillis();
        System.out.println("小的循环里面循环大的:"+(l1-l));
    }
    public static Integer bigToLittle(List<Integer> list1,List<Integer> list2){
        Integer x=0;
        for (int i = 0; i <list2.size() ; i++) {
            for (int j = 0; j <list1.size() ; j++) {
                x+=list1.get(j)*list2.get(i);
            }
        }
        return x;
    }
    public static Integer littleToBig(List<Integer> list1,List<Integer> list2){
        Integer x=0;
        for (int i = 0; i <list1.size() ; i++) {
            for (int j = 0; j <list2.size() ; j++) {
                x+=list2.get(j)*list1.get(i);
            }
        }
        return x;
    }
}
