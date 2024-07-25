package com.jsonTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author ming.li
 * @date 2023/7/26 15:45
 */
public class Demo2 {
    public static void main(String[] args) {
        JSONObject object=new JSONObject();
        object.put("ss","ss");
        JSONObject object1=new JSONObject();
        object1.put("ss1","ss1");
        JSONObject object2=new JSONObject();
        object2.put("ss2","ss2");
        JSONArray jsonArray=new JSONArray();
        jsonArray.add(object);
        jsonArray.add(object1);
        jsonArray.add(object2);
        System.out.println(jsonArray.toJSONString());
        JSONObject object3=new JSONObject();
        object3.put("ss","ss");
        //通过具体对象删除
        jsonArray.remove(object3);
        System.out.println("通过具体对象删除:"+jsonArray.toJSONString());
        //通过下标删除
        jsonArray.remove(1);
        System.out.println("通过下标删除:"+jsonArray.toJSONString());
        //通过下标插入，原先的值往后延
        jsonArray.add(0,object3);
        System.out.println("通过下标插入，原先的值往后延:"+jsonArray.toJSONString());
        //通过下标替换
        jsonArray.set(0,object2);
        System.out.println("通过下标替换:"+jsonArray.toJSONString());
    }
}
