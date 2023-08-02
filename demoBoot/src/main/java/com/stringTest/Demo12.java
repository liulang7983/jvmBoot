package com.stringTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enumUtil.OcrTypeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ming.li
 * @date 2023/8/2 18:45
 */
public class Demo12 {
    public static void main(String[] args) {
        //System.out.println(OcrTypeEnum.INVOICE);
        String str="{\n" +
                "  \"engine\":\"00000001,00000008,00000009\",\n" +
                "  \"patters\":[{\n" +
                "    \"name\": \"00000001.address,00000001.id\",\n" +
                "    \"pattern\": \"4,-4\"\n" +
                "  },{\n" +
                "    \"name\": \"00000001.name,00000009.收款人,00000009.开票人\",\n" +
                "    \"pattern\": \"1,1\"\n" +
                "  },{\n" +
                "    \"name\": \"00000009.发票代码,00000009.发票号码,00000009.购买方名称,00000009.购买方识别号,00000009.销售方名称,00000009.销售方识别号,00000009.销售方地址、电话,00000009.销售方开户行及账号,00000009.购买方地址、电话,00000009.购买方开户行及账号\",\n" +
                "    \"pattern\": \"-6,0\"\n" +
                "  }]\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(jsonObject);
        JSONObject json=new JSONObject();
        String engine = jsonObject.getString("engine");
        json.put("engine",engine);
        JSONArray patters = jsonObject.getJSONArray("patters");
        for (int i = 0; i < patters.size(); i++) {
            JSONObject object = patters.getJSONObject(i);
            String name = object.getString("name");
            String pattern = object.getString("pattern");
            String[] split = name.split(",");
            for (int j = 0; j < split.length; j++) {
                String s = split[j];
                int index = s.indexOf(".");
                String key = s.substring(0,index);
                String value = s.substring(index+1);
                if (json.containsKey(key)){
                    JSONObject object1 = json.getJSONObject(key);
                    object1.put(value,pattern);
                }else {
                    JSONObject object1 =new JSONObject();
                    object1.put(value,pattern);
                    json.put(key,object1);
                }
            }
        }
        System.out.println(json);
    }
}
