package com.listTest;

import com.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ming.li
 * @Date 2024/5/6 16:18
 * @Version 1.0
 */
public class Demo2 {
    public static void main(String[] args) {
        User user1 = new User("张三", 1);
        User user2 = new User("李四", 2);
        User user3 = new User("王五", 3);
        User user4 = new User("王五", 4);
        List<User> list=new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        String collect = list.stream().map(User::getName).distinct().collect(Collectors.joining(","));
        System.out.println(collect);
    }
}
