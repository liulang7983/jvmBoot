package com.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;

public class Demo5 {
    public static void main(String[] args) throws Exception{
        // 创建一个包含多个元素的 JSONArray
        JSONArray jsonArray = new JSONArray();
        JSONObject o1=new JSONObject();
        o1.put("name","o1");
        JSONObject o2=new JSONObject();
        o2.put("name","o2");
        JSONObject o3=new JSONObject();
        o3.put("name","o3");
        jsonArray.add(o1);
        jsonArray.add(o2);
        jsonArray.add(o3);

        System.out.println("原始 JSONArray：" + jsonArray);

        Iterator<Object> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JSONObject element = (JSONObject)iterator.next();

            if ("o1".equals(element.getString("name"))) {
                iterator.remove();
            }
            if ("o3".equals(element.getString("name"))) {
                iterator.remove();
            }
        }

        System.out.println("删除后的 JSONArray：" + jsonArray);
    }
}
