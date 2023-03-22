package com.timeTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ming.li
 * @date 2023/3/22 15:31
 * 获取六个月前的数据
 */
public class Demo1 {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        System.out.println(simpleDateFormat.format(date));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -6);
        Date time = cal.getTime();
        System.out.println(simpleDateFormat.format(time));
        if (date.before(time)){
            System.out.println("date在time之前");
        }else {
            System.out.println("date在time之后");
        }
    }
}
