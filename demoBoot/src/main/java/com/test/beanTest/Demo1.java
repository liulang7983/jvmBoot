package com.test.beanTest;

import com.bean.User2;

/**
 * @author ming.li
 * @date 2023/11/1 9:07
 */
public class Demo1 {
    public static void main(String[] args) {
        User2 user2 = new User2();
        System.out.println(user2.getMessage());
        user2.setRet_image(false);
        System.out.println(user2.getRet_image());
        if (user2.getRet_image()==null||user2.getRet_image()){
            System.out.println("我是空或者null");
        }
    }
}
