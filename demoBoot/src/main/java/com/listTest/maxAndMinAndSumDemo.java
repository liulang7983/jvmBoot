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
        list.add(new User(1,"lm1","lm11"));
        list.add(new User(2,"lm2","lm22"));
        list.add(new User(3,"lm3","lm33"));
        list.add(new User(4,"lm4","lm44"));
        list.add(new User(5,"lm5",null));
        list.add(new User(6,"lm6","lm66"));
        list.add(new User(7,"lm6","lm77"));
        Integer id = list.stream().max(Comparator.comparing(User::getId)).get().getId();
        System.out.println(id);
        Integer id1 = list.stream().min(Comparator.comparing(User::getId)).get().getId();
        System.out.println(id1);
        Integer sum = list.stream().map(e -> e.getId()).reduce(Integer::sum).get();
        System.out.println(sum);
    }
}
