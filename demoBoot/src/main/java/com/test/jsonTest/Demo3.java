package com.test.jsonTest;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ming.li
 * @date 2023/7/26 15:38
 */
public class Demo3 {
    public static void main(String[] args) {
        JSONObject object=new JSONObject();
        System.out.println(object.toString());
        object.remove("ss");
        System.out.println(object.toString());
        object.put("ss","ss");
        System.out.println(object);
        object.remove("ss");
        System.out.println(object);
        System.out.println(object.toString());
        String s="{}";
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println(jsonObject);
        jsonObject.remove("ss");
    }
}
