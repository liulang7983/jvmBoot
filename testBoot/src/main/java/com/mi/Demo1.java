package com.mi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.util.FileUtil;

public class Demo1 {
    public static void main(String[] args) {
        String s = FileUtil.readJsonFile();
        JSONObject jsonObject = JSONObject.parseObject(s);
        if (!jsonObject.getInteger("errorcode").equals(0)){
            return;
        }
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray pages = data.getJSONArray("pages");
        for (int i = 0; i <pages.size() ; i++) {
            JSONObject object = pages.getJSONObject(i);
            Integer errorcode = object.getInteger("errorcode");
        }
    }
}
