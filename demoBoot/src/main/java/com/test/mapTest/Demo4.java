package com.test.mapTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author ming.li
 * @Date 2024/11/4 10:43
 * @Version 1.0
 */
public class Demo4 {
    public static void main(String[] args) {
        Map<String, String> keys = new HashMap<>();
        keys.put("租赁物清单", "序号");
        keys.put("还款计划概算表", "还款计划概算表");
        Iterator<Map.Entry<String, String>> iterator = keys.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey());
        }
        System.out.println("==========");
        Map<String, String> keys2 = new LinkedHashMap<>();
        keys2.put("租赁物清单", "序号");
        keys2.put("还款计划概算表", "还款计划概算表");
        Iterator<Map.Entry<String, String>> iterator2 = keys2.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry<String, String> next = iterator2.next();
            System.out.println(next.getKey());
        }
    }
}
