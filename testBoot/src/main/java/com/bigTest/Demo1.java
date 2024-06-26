package com.bigTest;

import java.math.BigDecimal;

/**
 * @Author ming.li
 * @Date 2024/5/7 9:30
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        Float a=23.2f;
        Float b=23.2f;
        BigDecimal multiply = new BigDecimal(String.valueOf(a)).multiply(new BigDecimal(String.valueOf(b)));
        System.out.println(multiply);
    }
}
