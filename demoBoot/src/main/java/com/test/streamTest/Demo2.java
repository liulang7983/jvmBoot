package com.test.streamTest;

import com.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ming.li
 * @Date 2025/1/17 13:55
 * 按照条件刷选
 * @Version 1.0
 */
public class Demo2 {
    public static void main(String[] args) {
        List<User> list=new ArrayList<>();
        list.add(new User(3L,"张三","xxx"));
        list.add(new User(4L,"张四","xxx"));
        list.add(new User(8L,"张八","xxx"));
        list.add(new User(1L,"张一","xxx"));
        List<User> list1 = list.stream().filter(o -> o.getName().contains("一")).collect(Collectors.toList());
        System.out.println("名字存在一的个数:"+list1.size());

    }
}
