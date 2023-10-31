package com.listTest;

import com.bean.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ming.li
 * @date 2023/10/13 13:40
 */
public class Demo5 {
    public static void main(String[] args) {
        Map<String, List<User>> listMap=new HashMap<>();
        List<User> list1=new ArrayList<>();
        list1.add(new User(1L,"1","1"));
        listMap.put("list1",list1);
        List<User> list = listMap.get("list2");
        if (list==null||list.size()==0){
            System.out.println("为空");
        }
    }
}
