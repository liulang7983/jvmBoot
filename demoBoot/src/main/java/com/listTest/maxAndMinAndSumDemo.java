package com.listTest;

import com.bean.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author ming.li
 * @date 2023/6/15 19:58
 */
public class maxAndMinAndSumDemo {
    public static void main(String[] args) {
        List<User> list=new ArrayList<>();
        list.add(new User(1L,"lm1","lm11"));
        list.add(new User(2L,"lm2","lm22"));
        list.add(new User(3L,"lm3","lm33"));
        list.add(new User(4L,"lm4","lm44"));
        list.add(new User(5L,"lm5",null));
        list.add(new User(6L,"lm6","lm66"));
        list.add(new User(7L,"lm6","lm77"));
        Long id = list.stream().max(Comparator.comparing(User::getId)).get().getId();
        System.out.println(id);
        Long id1 = list.stream().min(Comparator.comparing(User::getId)).get().getId();
        System.out.println(id1);
        Long sum = list.stream().map(e -> e.getId()).reduce(Long::sum).get();
        System.out.println(sum);
    }
}
