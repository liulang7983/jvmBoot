package com.test.mapTest;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ming.li
 * @Date 2024/5/31 10:38
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        Map map=new HashMap();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("zz","2");
        BigDecimal zz = jsonObject.getBigDecimal("zz");
        System.out.println(zz);
        BigDecimal ss = jsonObject.getBigDecimal("ss");
        System.out.println(ss);
        Boolean oo = jsonObject.getBoolean("OO");
        System.out.println(oo);
        List<String> list=new ArrayList<>();
        list.add("SS");
        System.out.println(list.contains("ss"));

    }
}
