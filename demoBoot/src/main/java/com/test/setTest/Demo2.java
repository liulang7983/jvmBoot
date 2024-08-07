package com.test.setTest;

import java.util.Set;
import java.util.TreeSet;

/**
 * @Author ming.li
 * @Date 2024/7/24 16:36
 * @Version 1.0
 */
public class Demo2 {
    public static void main(String[] args) {
        Set<String> set=new TreeSet<>();
        set.add("张三");
        set.add(null);
    }
}
