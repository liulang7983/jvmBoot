package com.listTest;

import com.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ming.li
 * @date 2023/6/15 19:43
 */
public class DistinctDemo {
    public static void main(String[] args) {
        List<User> list=new ArrayList<>();
        list.add(new User(1,"lm1","lm11"));
        list.add(new User(2,"lm2","lm22"));
        list.add(new User(3,"lm3","lm33"));
        list.add(new User(4,"lm4","lm44"));
        list.add(new User(5,"lm5",null));
        list.add(new User(6,"lm6","lm66"));
        list.add(new User(7,"lm6","lm77"));
        List<String> collect = list.stream().map(User::getName).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }
}
