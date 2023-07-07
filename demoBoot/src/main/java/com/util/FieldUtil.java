package com.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.InvoiceGoods;

import java.lang.reflect.Field;
import java.util.List;

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

    public static String fieldCompare1(Field[] declaredFields,Object o1,Object o2) {
        for (Field field:declaredFields){
            field.setAccessible(true);
            String name = field.getName();
            System.out.println(name);
            try {
                if (name.equals("invoiceGoodsList")){
                    System.out.println(field.get(o1).toString());
                    List<InvoiceGoods> list=(List<InvoiceGoods>)field.get(o1);
                    List<InvoiceGoods> list1=(List<InvoiceGoods>)field.get(o1);
                    if (list.size()!=list.size()){
                        return "2";
                    }
                    for (int i = 0; i < list.size(); i++) {
                        InvoiceGoods invoiceGoods1 = list.get(i);
                        InvoiceGoods invoiceGoods2 = list1.get(i);
                        Field[] declaredFields1 = InvoiceGoods.class.getDeclaredFields();
                        for (Field field1:declaredFields1){
                            field1.setAccessible(true);
                            String name1 = field1.getName();
                            Object obj1 = field1.get(invoiceGoods1);
                            Object obj2 = field1.get(invoiceGoods2);
                            if (obj1==null&&obj2==null){
                                continue;
                            }
                            if (obj1!=null&&obj2!=null){
                                String str1 = obj1.toString();
                                String str2 = obj2.toString();
                                if (!str1.equals(str2)){
                                    return "2";
                                }
                            }else {
                                //一个为空
                                return "2";
                            }
                        }
                    }
                }else {
                    System.out.println(field.get(o1));

                    Object obj1 = field.get(o1);
                    Object obj2 = field.get(o2);
                    if (obj1==null&&obj2==null){
                        continue;
                    }
                    if (obj1!=null&&obj2!=null){
                        String str1 = obj1.toString();
                        String str2 = obj2.toString();
                        if (!str1.equals(str2)){
                            return "2";
                        }
                    }else {
                        //一个为空
                        return "2";
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "1";
    }
}
