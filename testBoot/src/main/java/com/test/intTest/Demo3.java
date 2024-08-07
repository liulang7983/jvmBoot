package com.test.intTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo3 {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("10");
        stringList.add("34");
        stringList.add("67");
        stringList.add("84");
        stringList.add("1");
        System.out.println("（String类型）最大值=" + Collections.max(stringList));
        System.out.println("（String类型）最小值=" + Collections.min(stringList));

        List<Integer> integerList = new ArrayList<>();
        integerList.add(5);
        integerList.add(38);
        integerList.add(52);
        integerList.add(88);
        integerList.add(100);
        System.out.println("（Integer类型）最大值=" + Collections.max(integerList));
        System.out.println("（Integer类型）最小值=" + Collections.min(integerList));

        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1.5);
        doubleList.add(45.6);
        doubleList.add(88.08);
        doubleList.add(55.2);
        doubleList.add(100.01);
        System.out.println("（Double类型）最大值=" + Collections.max(doubleList));
        System.out.println("（Double类型）最小值=" + Collections.min(doubleList));
    }
}
