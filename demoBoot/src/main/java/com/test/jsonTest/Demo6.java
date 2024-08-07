package com.test.jsonTest;

import com.alibaba.fastjson.JSONObject;

public class Demo6 {
    public static void main(String[] args) {
        String s="{\"col6\":{\"col\":6,\"rect\":\"1619,326,61,18\",\"errorMark\":true,\"origText\":\"70.75\",\"text\":\"70.75\",\"textcoords\":\"1621,326,57,18\",\"textRect\":\"1621,326,57,18\"},\"col7\":{\"col\":7,\"rect\":\"1737,326,44,18\",\"origText\":\"乙类\",\"text\":\"乙类\",\"textcoords\":\"1737,326,41,18\",\"textRect\":\"1737,326,41,18\"},\"col4\":{\"col\":4,\"rect\":\"1407,326,65,17\",\"errorMark\":false,\"origText\":\"70.75\",\"text\":\"70.75\",\"textcoords\":\"1407,326,66,17\",\"textRect\":\"1407,326,66,17\"},\"col5\":{\"col\":5,\"rect\":\"1515,326,55,17\",\"errorMark\":true,\"origText\":\"1.00\",\"text\":\"1.00\",\"textcoords\":\"1516,326,52,17\",\"textRect\":\"1516,326,52,17\"},\"rows\":34,\"col2\":{\"col\":2,\"rect\":\"1048,325,282,19\",\"origText\":\"盐酸右美托咪定注射液([2ml:0.2m支\",\"text\":\"盐酸右美托咪定注射液([2ml:0.2m支\",\"textcoords\":\"1050,325,280,19\",\"textRect\":\"1050,325,280,19\"},\"col3\":{\"col\":3,\"rect\":\"1301,325,55,19\",\"origText\":\"n支\",\"text\":\"n支\",\"textcoords\":\"1301,325,34,19\",\"textRect\":\"1301,325,34,19\"},\"col1\":{\"col\":1,\"rect\":\"961,327,57,17\",\"origText\":\"药品费\",\"text\":\"药品费\",\"textcoords\":\"961,327,59,17\",\"textRect\":\"961,327,59,17\"}}";
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println(jsonObject);

    }
}
