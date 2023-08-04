package com.jsonTest;

import com.alibaba.fastjson.JSONObject;

import java.util.Set;

/**
 * @author ming.li
 * @date 2023/7/26 15:38
 */
public class Demo3 {
    public static void main(String[] args) {
        JSONObject object=new JSONObject();
        object.put("ss","ss");
        object.put("ss1","ss1");
        object.put("ss2","ss2");
        Set<String> strings = object.keySet();
        for (String str:strings){
            System.out.println(str);
        }
    }
}
