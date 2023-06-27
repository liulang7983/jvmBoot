package com.listTest;

import com.alibaba.excel.util.StringUtils;
import com.bean.User;
import org.apache.poi.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ming.li
 * @date 2023/5/18 10:50
 */
public class Demo1 {
    public static void main(String[] args) {
        List<User> list=new ArrayList<>();
        list.add(new User(1,"lm1","lm11"));
        list.add(new User(2,"lm2","lm22"));
        list.add(new User(3,"lm3","lm33"));
        list.add(new User(4,"lm4","lm44"));
        list.add(new User(5,"lm5",null));
        list.add(new User(6,"lm6","lm66"));
        //获得message的集合
        List<String> collect = list.stream().map(User::getMessage).collect(Collectors.toList());
        System.out.println(collect);
        String join = StringUtil.join(",", collect);
        System.out.println("join:"+join);
        String s = list.stream().map(User::getMessage).toString();
        System.out.println(s);
        System.out.println("----");
        String collect1 = list.stream().map(User::getMessage).collect(Collectors.joining(","));
        System.out.println(collect1);
        System.out.println(list.get(list.size()-1));

        String collect2 = list.stream().map(User::getMessage).collect(Collectors.joining("\r\n"));
        String replace = collect2.replace("null", "");
        System.out.println("换行符："+collect2);
        System.out.println("-------------");
        System.out.println(replace);
    }
}
