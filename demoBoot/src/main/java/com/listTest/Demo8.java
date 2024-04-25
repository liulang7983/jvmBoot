package com.listTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2024/3/26 14:09
 * @Version 1.0
 */
public class Demo8 {
    public static void main(String[] args) {
        List<Long> list=new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(5L);
        list.add(8L);
        list.add(5L);
        list.remove(5L);
        System.out.println(list);

    }
}
