package com.test.listTest;

import java.util.ArrayList;
import java.util.List;

public class Demo1 {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j <list.size() ; j++) {
                System.out.println(list.get(j));
                if (list.get(j)==3){
                    break;
                }
            }

        }
    }
}
