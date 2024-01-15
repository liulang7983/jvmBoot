package com.json;

import com.alibaba.fastjson.JSONArray;
import com.bean.User;

import java.util.ArrayList;
import java.util.List;

public class Demo1 {
    public static void main(String[] args) {
        List<User> list=new ArrayList<>();
        list.add(new User("张三",1));
        list.add(new User("李四",12));
        JSONArray jsonArray = JSONArray.parseArray(JSONArray.toJSON(list).toString());
        System.out.println(jsonArray);
    }
}
