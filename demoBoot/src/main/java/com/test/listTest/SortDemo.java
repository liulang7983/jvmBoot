package com.test.listTest;

import com.bean.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ming.li
 * @date 2023/6/13 14:36
 */
public class SortDemo {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "lm1", "lm11"));
        list.add(new User(5L, "lm2", "lm22"));
        list.add(new User(6L, "lm3", "lm33"));
        list.add(new User(7L, "lm4", "lm44"));
        list.add(new User(2L, "lm5", null));
        list.add(new User(6L, "lm6", "lm66"));
        //升序
        List<User> collect = list.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(list);
        //升序
        list.sort((o1,o2)->o1.getId().compareTo(o2.getId()));
        System.out.println(list);
        //降序
        List<User> collect1 = list.stream().sorted(Comparator.comparing(User::getId).reversed()).collect(Collectors.toList());
        System.out.println(collect1);
    }
}
