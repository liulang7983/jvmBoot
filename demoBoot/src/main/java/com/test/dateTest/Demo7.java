package com.test.dateTest;

import com.util.DateTimeUtil;

import java.util.Date;

/**
 * @Author ming.li
 * @Date 2025/8/26 10:50
 * @Version 1.0
 */
public class Demo7 {
    public static void main(String[] args) {
        String s = DateTimeUtil.formatDate(new Date());
        System.out.println(s);
    }
}
