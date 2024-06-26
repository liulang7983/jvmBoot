package com.mapTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ming.li
 * @Date 2024/6/5 22:16
 * @Version 1.0
 */
public class Demo2 {
    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<>();
        List<String> list=new ArrayList<>();
        list.add("ss");
        map.put("list",list);
        map.put("ss","ss");
        System.out.println(map);
        List<String> list1=(List<String>)map.get("list");
        list1.add("zzzz");
        System.out.println(map);
        Object ss = map.get("ss");
        ss="zzz";
        System.out.println(map);

    }
}
