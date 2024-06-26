package com.listTest;

import com.bean.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ming.li
 * @Date 2024/5/30 14:28
 * @Version 1.0
 */
public class Demo3 {
    public static void main(String[] args) {
        List<User> list=new ArrayList<>();
        list.add(new User("1",1,1));
        list.add(new User("3",3,3));
        list.add(new User("2",2,2));
        list.add(new User("2",2,4));
        List<User> collect = list.stream().sorted(Comparator.comparing(User::getCount).thenComparing(Comparator.comparing(User::getIndex).reversed())).collect(Collectors.toList());
        list.add(new User());
    }
}
