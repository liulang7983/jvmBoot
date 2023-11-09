package com.byteToString;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ming.li
 * @date 2023/11/9 10:11
 */
public class Demo1 {
    public static void main(String[] args) {
        JSONObject json=new JSONObject();
        json.put("张","张三");
        json.put("李四","李四");
        byte[] bytes = json.toString().getBytes();
        String s = new String(bytes);
        System.out.println(s);
    }
}
