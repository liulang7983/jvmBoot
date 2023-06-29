package com.timeTest;

import com.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ming.li
 * @date 2023/3/22 15:31
 * 获取六个月前的数据
 */
public class Demo1 {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        System.out.println(simpleDateFormat.format(date));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -6);
        Date time = cal.getTime();
        System.out.println(simpleDateFormat.format(time));
        //此时的天数存在BUG,当昨天的时间是在今天之后，那就回少一天
        Date start=simpleDateFormat.parse("2023-06-28 23:24:24");
        Date end=new Date();
        Integer integer = DateUtil.periodCount(1, start, end);
        System.out.println(integer);
        Integer integer1 = DateUtil.intervalDay(start, end);
        System.out.println(integer1);
    }
}
