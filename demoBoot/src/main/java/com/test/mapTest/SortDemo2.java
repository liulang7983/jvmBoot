package com.test.mapTest;

import java.util.*;

/**
 * @author ming.li
 * @date 2023/6/19 9:46
 */
public class SortDemo2 {
    public static void main(String[] args) {
        //map的key排序
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "2");
        map.put(0, "s");
        map.put(5, "e");
        map.put(1, "d");
        Map<Integer, String> result = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        for (String value : result.values()) {
            System.out.println(value);
        }
    }
}
