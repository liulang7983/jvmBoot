package com.intTest;

import com.bean.User;

public class Demo2 {
    public static void main(String[] args) {
        User user = new User();
        Integer a=1;
        user.setCount(a);
        System.out.println(user.getCount());
        user.setCount(a++);
        System.out.println(user.getCount());
        user.setCount(++a);
        System.out.println(user.getCount());
    }
}
