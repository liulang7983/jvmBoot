package com.mi;

import com.bean.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Demo4 {
    public static void main(String[] args) {
        List<User> list=new ArrayList<>(16);
        list.add(new User("张三",1));
        list.add(new User("张三",2));
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()) {
            User next = iterator.next();
            if (next.getCount().equals(1)){
                iterator.remove();
            }
            System.out.println(next.getCount());
        }

    }
}
