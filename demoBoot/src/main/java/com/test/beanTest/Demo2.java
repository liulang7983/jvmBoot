package com.test.beanTest;

import com.bean.User;

/**
 * @Author ming.li
 * @Date 2025/8/25 17:31
 * @Version 1.0
 */
public class Demo2 {
    public static   void main(String[] args) {
        // TODO Auto-generated method stub
        User s1 = new User("⼩张");
        User s2 = new User("⼩李");
        swap(s1, s2);
        System.out.println("s1:" + s1.getName());
        System.out.println("s2:" + s2.getName());
    }
    public static void swap(User s1, User s2) {
        User temp = s1;
        s1 = s2;
        s2 = temp;
        System.out.println("s1:" + s1.getName());
        System.out.println("s2:" + s2.getName());
    }
}
