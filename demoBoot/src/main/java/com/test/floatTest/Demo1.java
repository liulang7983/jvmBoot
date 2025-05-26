package com.test.floatTest;

import java.math.BigDecimal;

/**
 * @Author ming.li
 * @Date 2025/5/12 9:17
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        float total = 0.0f;
        for (int i = 0; i < 10; i++) {
            total += 0.1f;
        }
        System.out.println(total); // 输出 0.99999994 (而非预期的 1.0)
        System.out.println(new BigDecimal(String.valueOf(total)));
    }
}
