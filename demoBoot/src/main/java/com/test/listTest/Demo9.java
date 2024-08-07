package com.test.listTest;

import com.bean.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2024/6/14 11:20
 * @Version 1.0
 */
public class Demo9 {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "lm1", "lm11xx"));
        list.add(new User(5L, "lm2", "lm22x"));
        list.add(new User(5L, "lm5", "lm55xdsex"));
        //降序排序
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.getMessage().length()-o1.getMessage().length();
            }
        });
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i).getMessage());
        }
    }
}
