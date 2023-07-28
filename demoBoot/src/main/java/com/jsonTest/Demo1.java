package com.jsonTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author ming.li
 * @date 2023/7/26 15:38
 */
public class Demo1 {
    public static void main(String[] args) {
        JSONObject object=new JSONObject();
        object.put("ss","ss");
        object.put("ss1","ss1");
        object.put("ss2","ss2");
        System.out.println(object.toJSONString());
        object.remove("ss1");
        System.out.println(object.toJSONString());
    }
}
