package com.listTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ming.li
 * @date 2023/6/13 14:36
 */
public class Demo2 {
    public volatile Integer a=0;
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");
        list.add("田七");
        list.add("王八");
        list.add("九哥");
        test1(list);
    }
    public static void test1(List list){
        System.out.println("我是记录者");
        test2(list);
    }

    public static void test2(List list){
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println(list.get(i));

            if (i<size-1){
                System.out.println("我是记录者");
            }
        }
    }
}
