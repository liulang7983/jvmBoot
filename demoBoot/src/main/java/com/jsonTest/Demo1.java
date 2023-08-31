package com.jsonTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.User;

/**
 * @author ming.li
 * @date 2023/7/26 15:38
 */
public class Demo1 {
    public static void main(String[] args) {
        JSONObject object=new JSONObject();
        object.put("name","ss");
        User user = JSONObject.parseObject(object.toString(), User.class);
        System.out.println(user);

    }
}
