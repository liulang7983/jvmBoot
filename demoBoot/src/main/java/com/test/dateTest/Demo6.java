package com.test.dateTest;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author ming.li
 * @Date 2025/1/7 17:39
 * @Version 1.0
 */
public class Demo6 {
    @Test
    public void test1()throws Exception{
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = "2023-12-21 13:59:59";
        Date startDate1 = format.parse(start);
        //过去七天
        c.setTime(startDate1);
        c.add(Calendar.DATE, - 7);
        Date d = c.getTime();
        String day = format.format(d);
        System.out.println("过去七天："+day);
    }
    @Test
    public void test2()throws Exception{
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = "2023-12-21 13:59:59";
        Date startDate1 = format.parse(start);
        //这个会弄成这个日期的月初1号，再减去七天
        c.setTime(startDate1);
        c.set(Calendar.DATE, - 7);
        Date d = c.getTime();
        String day = format.format(d);
        System.out.println("过去七天："+day);
    }
}
