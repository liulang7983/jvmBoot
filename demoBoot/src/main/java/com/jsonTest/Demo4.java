package com.jsonTest;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author ming.li
 * @date 2023/8/15 16:21
 */
public class Demo4 {
    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","张三");
        JSONObject jsonObject2=new JSONObject();
        jsonObject2.put("name","张三");
        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject2);
        System.out.println(jsonArray);
        System.out.println(jsonArray.remove(1));
    }
}
