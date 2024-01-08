package com.mi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.util.FileUtil;

import java.util.*;

public class Demo3 {
    public static void main(String[] args) {
        String s = FileUtil.readJsonFile();
        JSONArray ocrObjArry = JSONObject.parseArray(s);
        List<JSONObject> list=new ArrayList<>();
        list.add(ocrObjArry.getJSONObject(0));
        for (int i = 0; i < ocrObjArry.size(); i++) {
            JSONObject jsonObject = ocrObjArry.getJSONObject(i);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray pages = data.getJSONArray("pages");
            for (int j = 0; j < pages.size(); j++) {
                JSONObject pagesJson= pages.getJSONObject(j);
                Map<Integer,JSONArray> map=new HashMap<>();
                JSONArray tables = pagesJson.getJSONArray("tables");
                for (int k = 0; k <tables.size() ; k++) {
                    JSONObject tableJson = tables.getJSONObject(k);
                    JSONArray rows = tableJson.getJSONArray("rows");
                    map.put(k,rows);
                }
                Set<Integer> integers = map.keySet();
                JSONArray jsonArray=map.get(0);
                int size = jsonArray.size();
                for (Integer index:integers) {
                    if (!index.equals(0)){
                        JSONArray array = map.get(index);
                        for (int k = 0; k < array.size(); k++) {
                            JSONObject object = array.getJSONObject(k);
                            object.put("row",++size);
                            jsonArray.add(object);
                        }
                    }
                }
                for (int k = 0; k < jsonArray.size(); k++) {
                    JSONArray cells = jsonArray.getJSONObject(k).getJSONArray("cells");
                    for (int l = 0; l < cells.size(); l++) {
                        JSONObject cell = cells.getJSONObject(l);
                        cell.put("ocrtext",cell.getString("text"));
                    }
                }
                JSONArray newTable=new JSONArray();
                newTable.add(tables.getJSONObject(0));
                pagesJson.put("tables",newTable);
                int size1 = newTable.size();
            }
        }
    }
}
