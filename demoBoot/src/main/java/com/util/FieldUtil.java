package com.util;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;

/**
 * @author ming.li
 * @date 2023/5/31 18:15
 */
public class FieldUtil {

    public static JSONObject fieldCompare(Field[] declaredFields,Object o1,Object o2) {
        for (Field field:declaredFields){
            field.setAccessible(true);
            String name = field.getName();
            System.out.println(name);
            JSONObject jsonObject = new JSONObject();
            try {
                String name1 = String.valueOf(field.get(o1));
                String name2 = String.valueOf(field.get(o2));

                System.out.println("name1:"+name1);
                System.out.println("name12:"+name2);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
