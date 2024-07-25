package com.jsonTest;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ming.li
 * @date 2023/10/9 16:03
 */
public class Demo5 {
    //JSONObject是引用传递
    public static void main(String[] args) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("ss","zz");
        change(jsonObject);
        System.out.println(jsonObject);

    }
    public static void change(JSONObject jsonObject){
        jsonObject.put("ss","zaz");
    }
}
