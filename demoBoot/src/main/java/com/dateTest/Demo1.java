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
    public static void main(String[] args) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String start = "2023-06-29";
        String end = "2023-06-29";
        Date startDate = format.parse(start);
        Date endtDate = format.parse(end);
        Integer period = 1;
        Integer integer = DateUtil.periodCount(period, startDate, endtDate);
        for (int i = 0; i < integer; i++) {
            Map<String, String> monthMap = DateUtil.periodNextDate(startDate, endtDate, period, i);
            System.out.println("按照周期获取开始和结束时间月");
            System.out.println("startDate:" + monthMap.get("startDate"));
            System.out.println("endDate:" + monthMap.get("endDate"));
            System.out.println("------------");
        }
        Date ends = new Date();
        Integer i1 = DateUtil.periodCount(period, startDate, endtDate);
        Integer i2 = DateUtil.periodCount(period, startDate, ends);
        System.out.println("i1:" + i1 + ",i2:" + i2);

    }
}
