package com.test.listTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2024/6/21 19:48
 * @Version 1.0
 */
public class Demo10 {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.add(2);
        if (list.contains(null)){
            System.out.println("存在");
        }else {
            System.out.println("不存在");
        }
    }
}
