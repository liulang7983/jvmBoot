package com.test.setTest;

import java.util.Set;
import java.util.TreeSet;

public class Demo1 {
    public static void main(String[] args) {
        Set<Integer> set=new TreeSet<>();
        set.add(45);
        set.add(3);
        set.add(5);
        System.out.println(set);
        set.remove(3);
        System.out.println(set);
        String s="sdfd";
        String sd=null;
        if (s.contains(sd)){
            System.out.println("存在");
        }
    }
}
