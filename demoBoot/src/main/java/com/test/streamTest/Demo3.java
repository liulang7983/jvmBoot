package com.test.streamTest;

import com.bean.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ming.li
 * @Date 2025/1/17 13:55
 * 按照某个字段排序
 * @Version 1.0
 */
public class Demo3 {
    public static void main(String[] args) {
        List<User> list=new ArrayList<>();
        list.add(new User(3L,"张三","xxx"));
        list.add(new User(4L,"张四","xxx"));
        list.add(new User(8L,"张八","xxx"));
        list.add(new User(1L,"张一","xxx"));
        List<User> collect = list.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
        for (int i = 0; i < collect.size(); i++) {
            System.out.println("正序:"+collect.get(i).getId());
        }
        collect = list.stream().sorted(Comparator.comparing(User::getId).reversed()).collect(Collectors.toList());
        for (int i = 0; i < collect.size(); i++) {
            System.out.println("倒序:"+collect.get(i).getId());
        }

    }
}
