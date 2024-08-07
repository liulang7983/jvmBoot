package com.test.mapTest;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Demo2 {
    public static void main(String[] args) {
        Map<Integer, String> map = new TreeMap<>();
        map.put(2, "2");
        map.put(0, "s");
        map.put(5, "e");
        map.put(1, "d");
        Set<Integer> integers = map.keySet();
        for (Integer s:integers){
            System.out.println(s);
        }
    }
}
