package com.test.mapTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author ming.li
 * @Date 2025/1/6 10:56
 * HashMap的key一个null
 * @Version 1.0
 */
public class Demo6 {
    public static void main(String[] args) {
        Map<String,String> map=new HashMap();
        map.put(null,"张三");
        map.put("1","李四");
        map.put(null,"王五");
        Set<String> set = map.keySet();
        for (String s:set){
            System.out.println("key:"+s+",value:"+map.get(s));
        }
    }
}
