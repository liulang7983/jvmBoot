package com.timeTest;


import cn.hutool.core.date.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * @author ming.li
 * @date 2023/8/11 15:14
 */
public class Demo3 {
    public static void main(String[] args) {
        long beginOfDay = DateUtil.beginOfDay(new Date()).getTime() / 1000;
        System.out.println(new Date(beginOfDay*1000));
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);
        long start=beginOfDay;
        for (int i = 0; i <= hour; i++) {
            System.out.println("start:"+new Date((start)*1000));
            start+=3600;
            System.out.println("end:"+new Date((start)*1000));
            System.out.println("==========");

        }


        System.out.println(new Date(1691683200000L));
    }
}
