package com.test.intTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Demo1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        // 添加元素到列表中（这里只作为示例）
        list.add(5);
        list.add(2);
        list.add(8);
        list.add(1);
        list.sort(Comparator.reverseOrder());
        for (Integer i:list){
            System.out.println(i);
        }
        Collections.sort(list);
        for (Integer i:list){
            System.out.println(i);
        }
    }
}
