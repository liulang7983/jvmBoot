package com.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Demo6 {
    public static void main(String[] args) {
        List<JSONObject> list=new ArrayList<>();
        JSONObject json1=new JSONObject();
        json1.put("name","张1");
        list.add(json1);
        JSONObject json2=new JSONObject();
        json2.put("name","张2");
        list.add(json2);
        JSONObject json3=new JSONObject();
        json3.put("name","张3");
        list.add(json3);
        System.out.println(list);
        JSONArray jsonArray=new JSONArray();
        JSONObject json4=new JSONObject();
        json4.put("name","张4");
        jsonArray.add(json4);
        System.out.println(jsonArray);
        jsonArray.addAll(list);
        System.out.println(jsonArray);
    }
}
