package com.test.dateTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author ming.li
 * @Date 2024/8/6 9:17
 * @Version 1.0
 */
public class Demo4 {
    public static void main(String[] args)throws Exception {
        //大写的YYYY会有问题
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        String start = "2023-12-31 23:59:59";
        Date startDate1 = format1.parse(start);
        System.out.println(startDate1);
        Date startDate2 = format2.parse(start);
        System.out.println(startDate2);
    }
}
