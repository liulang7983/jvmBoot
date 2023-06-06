package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.bean.CheckInvoice;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ming.li
 * @date 2023/6/1 16:31
 */
public class FileUtil {
    public static String readResourceFileToString(String fileName, String cs) {
        /*StringBuilder sb = new StringBuilder();
        try (InputStream is = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(is, cs)) {
            try (BufferedReader reader = new BufferedReader(isr)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("获取文件出错:"+e);
        }
        return sb.toString();*/
        StringBuilder sb = new StringBuilder();
        try (InputStream is = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
             InputStreamReader isr = new InputStreamReader(is, cs)) {
            try (BufferedReader reader = new BufferedReader(isr)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("获取文件出错:"+e);
        }
        return sb.toString();
    }

    public static String readFileToString(String fileName, String cs) {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(is, cs)) {
            try (BufferedReader reader = new BufferedReader(isr)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("获取文件出错:"+e);
        }
        return sb.toString();

    }

    public static CheckInvoice getCatByItems(String str){
        JSONObject message = JSONObject.parseObject(str);
        String strJson = readResourceFileToString("OcrFieldMap.json","utf-8");
        //获取保持顺序的json
        LinkedHashMap<String, Object> json = JSON.parseObject(strJson, LinkedHashMap.class, Feature.OrderedField);
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.putAll(json);
        JSONObject typeJsonObject = jsonObject.getJSONObject("cat");
        Set<String> typeKeySet = typeJsonObject.keySet();
        Map<String, String> map = new HashMap<>();//用来存放值
        for (String key : typeKeySet) {
            if (message.containsKey(key)) {
                map.put(typeJsonObject.getString(key), message.getString(key));
            }
        }
        String s = JSONObject.toJSONString(map);
        CheckInvoice checkInvoice = JSON.parseObject(s, CheckInvoice.class);
        return checkInvoice;
    }

    public static Map getChange(){
        JSONObject jsonstr=new JSONObject();
        String str=readResourceFileToString("1.json","utf-8");
        JSONObject message = JSONObject.parseObject(str);
        String strJson = readResourceFileToString("OcrFieldMap.json","utf-8");
        //获取保持顺序的json
        LinkedHashMap<String, Object> json = JSON.parseObject(strJson, LinkedHashMap.class, Feature.OrderedField);
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.putAll(json);
        JSONObject typeJsonObject = jsonObject.getJSONObject("cat");
        Set<String> typeKeySet = typeJsonObject.keySet();
        Map<String, JSONObject> map = new HashMap<>();//用来存放值
        for (String key : typeKeySet) {
            if (message.containsKey(key)) {
                jsonstr.put(typeJsonObject.getString(key), message.get(key));
            }
        }
        map.put("k1",message);
        map.put("k2",jsonstr);

        return map;
    }
}
