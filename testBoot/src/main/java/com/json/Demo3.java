package com.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Demo3 {
    public static void main(String[] args) {
        JSONArray jsonArray=new JSONArray();
        for (int i = 0; i <3 ; i++) {
            JSONObject object=new JSONObject();
            object.put(i+"",i);
            jsonArray.add(object);
        }
        System.out.println(jsonArray);
        JSONObject object=new JSONObject();
        object.put("4",4);
        jsonArray.add(3,object);
        System.out.println(jsonArray);
    }
}
