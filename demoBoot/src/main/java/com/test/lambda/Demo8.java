package com.test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ming.li
 * @Date 2024/10/31 11:31
 * @Version 1.0
 */
public class Demo8 {
    public static void main(String[] args) {
        // 不使用lambda表达式为每个订单加上12%的税
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            System.out.println(price);
        }
        System.out.println("------");
        // 使用lambda表达式
        List<Integer> costBeforeTax1 = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax1.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
        List<Double> collect = costBeforeTax1.stream().map((cost) -> cost + .12 * cost).collect(Collectors.toList());
        System.out.println("=======");
        collect.forEach(n-> System.out.println(n));
        System.out.println("==");
        collect.forEach(System.out::println);
    }

}
