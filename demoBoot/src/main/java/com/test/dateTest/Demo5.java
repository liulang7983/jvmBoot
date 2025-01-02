package com.test.dateTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ming.li
 * @Date 2024/12/24 15:39
 * @Version 1.0
 */
public class Demo5 {
    public static void main(String[] args)throws Exception {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String start = "2023-12-31 23:59:59";
        Date startDate1 = format1.parse(start);
        String start2 = "2023-12-30 23:59:59";
        Date startDate2 = format1.parse(start2);
        String start3 = "2013-12-30 23:59:59";
        Date startDate3 = format1.parse(start3);
        List<DateInfo> list=new ArrayList<>();
        list.add(new DateInfo(1,startDate1));
        list.add(new DateInfo(2,startDate2));
        list.add(new DateInfo(3,startDate3));
        List<DateInfo> collect = list.stream().sorted(Comparator.comparing(DateInfo::getDate).reversed()).collect(Collectors.toList());
        System.out.println(collect.size());
    }
}
