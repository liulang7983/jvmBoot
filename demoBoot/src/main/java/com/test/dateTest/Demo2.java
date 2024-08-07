package com.test.dateTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author ming.li
 * @date 2023/10/11 10:01
 */
public class Demo2 {
    public static SimpleDateFormat year=new SimpleDateFormat("yyyy年");
    public static SimpleDateFormat month=new SimpleDateFormat("yyyy年MM月");
    public static SimpleDateFormat day=new SimpleDateFormat("yyyy年MM月dd日");
    public static SimpleDateFormat test=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    public static void main(String[] args) {
        dateRange("2020年11月12日",1);
        dateRange("2020年11月",2);
        dateRange("2020年",3);
    }
    //获取开始和结束时间
    //统计周期 1-日 2-月 3-年
    public static void dateRange(String str,Integer period){
        Date date=null;
        try {
            switch (period){
                case 1:
                    date=day.parse(str);
                    break;
                case 2:
                    date=month.parse(str);
                    break;
                case 3:
                    date=year.parse(str);
                    break;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        Date theDate = calendar.getTime();
        gcLast.setTime(theDate);
        if (period==1){
            gcLast.add(Calendar.DATE, 1);
        }else  if (period==2){
            gcLast.add(Calendar.MONTH, 1);
        }else if (period==3){
            gcLast.add(Calendar.YEAR, 1);
        }
        Date time = gcLast.getTime();
        System.out.println(test.format(date));
        System.out.println(test.format(time));
    }
}
