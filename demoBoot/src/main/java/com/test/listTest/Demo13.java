package com.test.listTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2024/8/5 15:21
 * @Version 1.0
 */
public class Demo13 {
    public static void main(String[] args) {
        String[] arr={"1s","2s","3s"};
        List<String> list = Arrays.asList(arr);
        System.out.println(list);
        System.out.println(arr);
        //会报错
        //list.add("ss");
        //改变数组会改变list
        arr[0]="4s";
        System.out.println(list);
        List<String> list1=new ArrayList<>();
        list1.add("1s");
        list1.add("2s");
        list1.add("3s");
        list1.add("4s");
        List<String> list2 = list1.subList(1, 2);
        System.out.println(list1);
        System.out.println(list2);
        //改变原始list会影响到subLIst
        list1.set(1,"2ss");
        System.out.println(list1);
        System.out.println(list2);
        //添加会报错
        list1.add("sss");
        System.out.println(list1);
        //System.out.println(list2);

        List<String> list3=new ArrayList<>(list1.subList(1, 2));
        System.out.println(list3);
        BigDecimal bigDecimal = BigDecimal.valueOf(3.456f);
    }
}
