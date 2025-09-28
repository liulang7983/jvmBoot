package com.test.listTest1;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2025/9/15 11:17
 * @Version 1.0
 */
public class Demo3 {
    public static void main(String[] args) {
        List list=new ArrayList();
        for (int i = 0; i < 1000; i++) {
            if (i%10==0){
                list.add(null);
            }else {
                list.add(i);
            }
        }
        for (int i = 0; i <list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
