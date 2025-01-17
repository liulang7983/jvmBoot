package com.test.listTest;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @Author ming.li
 * @Date 2025/1/17 9:31
 * @Version 1.0
 */
public class Demo15 {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.add(2);
        list.add(5);
        list.add(9);
        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            iterator.set(4);
        }
        iterator = list.listIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
