package com.test.timeTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ming.li
 * @date 2023/10/10 9:58
 */
public class Demo5 {
    public static void main(String[] args) {
        String year = new SimpleDateFormat("YYYYMM").format(new Date());
        String year1 = new SimpleDateFormat("yyyyMM").format(new Date());
        System.out.println(year);
        System.out.println(year1);
    }
}
