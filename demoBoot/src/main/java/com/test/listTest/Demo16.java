package com.test.listTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2025/5/8 10:15
 * @Version 1.0
 */
public class Demo16 {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <120 ; i++) {
            list.add(i);
        }
        list=list.subList(0,100>list.size()?list.size():100);
        System.out.println(list);
    }
}
