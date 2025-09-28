package com.fail.Demo5;

import com.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2025/9/24 16:59
 * @Version 1.0
 */
public class DemoSucceed {
    public static void main(String[] args) {
        List<User> list=new ArrayList<>();
        List<User> list1=new ArrayList<>();
        for (int i = 0; i <1000 ; i++) {
            list.add(new User("我的位置是:"+i,i));
        }
        for (int i = 0; i <200000 ; i++) {
            list1.add(new User("我的位置是:"+i,i));
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i <list.size() ; i++) {
            final Integer x=list.get(i).getType();
            for (int j = 0; j < list1.size(); j++) {
                int a=x*list1.get(j).getType()*(x+list1.get(j).getType());
                int b=x+list1.get(j).getType()+(x+list1.get(j).getType());
                int c=x-list1.get(j).getType()-(x-list1.get(j).getType());
                int d=x*list1.get(j).getType()+(x-list1.get(j).getType());
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
