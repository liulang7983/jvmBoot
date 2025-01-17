package com.test.streamTest;

import com.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ming.li
 * @Date 2025/1/17 13:55
 * bean提取单字段或者去重
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        List<User> list=new ArrayList<>();
        list.add(new User(3L,"张三","xxx"));
        list.add(new User(4L,"张四","xxx"));
        list.add(new User(8L,"张八","xxx"));
        list.add(new User(1L,"张一","xxx"));
        list.add(new User(1L,"张一","xxx"));
        List<String> collect = list.stream().map(User::getName).collect(Collectors.toList());
        System.out.println("单字段:"+collect);
        collect = list.stream().map(User::getName).distinct().collect(Collectors.toList());
        System.out.println("单字段去重:"+collect);
    }
}
