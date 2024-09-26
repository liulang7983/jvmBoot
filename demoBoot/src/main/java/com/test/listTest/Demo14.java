package com.test.listTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2024/9/23 9:12
 * @Version 1.0
 */
public class Demo14 {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(list.size());
        list.clear();
        System.out.println(list.size());
    }
}
