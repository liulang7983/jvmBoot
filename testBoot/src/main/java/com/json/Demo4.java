package com.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Demo4 {
    public static void main(String[] args) {
        JSONObject object=new JSONObject();
        JSONArray ss = object.getJSONArray("ss");
        System.out.println(ss);


    }
}
