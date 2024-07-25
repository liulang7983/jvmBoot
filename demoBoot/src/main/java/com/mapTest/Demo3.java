package com.mapTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ming.li
 * @Date 2024/3/26 13:39
 * @Version 1.0
 */
public class Demo3 {
    public static void main(String[] args) {
        Map<Long,Integer> map=new HashMap<>();
        map.put(2L,2);
        map.put(3L,3);
        map.put(4L,4);
        map.put(5L,5);
        map.put(6L,6);
        map.put(5L,7);
        map.remove(5L);
        System.out.println(map);
    }
}
