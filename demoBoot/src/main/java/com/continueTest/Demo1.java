package com.continueTest;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ming.li
 * @date 2023/6/27 10:07
 */
public class Demo1 {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("-");
        list.add(",");
        list.add("/");
        list.add("[");
        list.add("]");
        list.add("|");
        System.out.println(test1(list));

    }
    public static String test1(List<String> list){
        String a="-----";
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            //存在此字符并且全是此字符
            if (a.contains(s)){
                String all = a.replaceAll(s, "");
                if (StringUtils.isBlank(all)){
                    a="";
                    continue;
                }
            }
        }
        System.out.println("完成");
        return a;
    }
}
