package com.dateTest;

import com.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author ming.li
 * @date 2023/5/19 15:46
 */
public class Demo1 {
    public static void main(String[] args)throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String start="2023-04-18 18:22:22";
        String end="2024-05-19 18:22:22";
        Date startDate = format.parse(start);
        Date endtDate = format.parse(end);
        /*System.out.println(DateUtil.periodCount(1,startDate,endtDate));
        System.out.println(DateUtil.periodCount(2,startDate,endtDate));
        System.out.println(DateUtil.periodCount(3,startDate,endtDate));
        System.out.println(DateUtil.periodCount(4,startDate,endtDate));
        System.out.println("按照周期获取开始和结束时间天");
        Map<String, Date> dateMap = DateUtil.periodNextDate(startDate, 1, 0);
        System.out.println("startDate:"+dateMap.get("startDate"));
        System.out.println("endDate:"+dateMap.get("endDate"));
        Map<String, Date> monthMap = DateUtil.periodNextDate(startDate, 2, 1);
        System.out.println("按照周期获取开始和结束时间月");
        System.out.println("startDate:"+monthMap.get("startDate"));
        System.out.println("endDate:"+monthMap.get("endDate"));
        System.out.println("按照周期获取开始和结束时间年");
        Map<String, Date> yearMap = DateUtil.periodNextDate(startDate, 3, 0);
        System.out.println("startDate:"+yearMap.get("startDate"));
        System.out.println("endDate:"+yearMap.get("endDate"));*/

     /*   System.out.println(DateUtil.changeDate(startDate,1));
        System.out.println(DateUtil.changeDate(startDate,2));
        System.out.println(DateUtil.changeDate(startDate,3));*/
        Integer period=1;
        Integer integer = DateUtil.periodCount(period, startDate, endtDate);
        for (int i = 0; i < integer; i++) {
            Map<String, Date> monthMap = DateUtil.periodNextDate(startDate, period, i);
            System.out.println("按照周期获取开始和结束时间月");
            System.out.println("startDate:"+monthMap.get("startDate"));
            System.out.println("endDate:"+monthMap.get("endDate"));
            System.out.println("------------");
        }



    }
}
