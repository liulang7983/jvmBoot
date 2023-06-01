package com.util;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ming.li
 * @date 2023/5/19 16:02
 */
public class DateUtil {

    //按照周期查看间隔的周期数
    //统计周期 1-日 2-月 4-季度 3-年(季度用的不多，暂时限制住)
    public static Integer periodCount(Integer period, Date startTime, Date endTime) {
        Integer value = null;
        if (startTime == null || endTime == null) {
            return value;
        }
        if (period.equals(1)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String start = format.format(startTime);
            String end = format.format(endTime);
            Long l = endTime.getTime() - startTime.getTime();
            Double v = l.doubleValue();
            Double v1 = v / (1000 * 3600 * 24);
            value = ((Double) Math.ceil(v1)).intValue();
            if (!start.equals(end)) {
                value++;
            }
        } else if (period.equals(2)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
            String start = format.format(startTime);
            String end = format.format(endTime);
            Integer startYear = Integer.valueOf(start.substring(0, 4));
            Integer endYear = Integer.valueOf(end.substring(0, 4));
            Integer startMonth = Integer.valueOf(start.substring(4, 6));
            Integer endMonth = Integer.valueOf(end.substring(4, 6));
            Integer year = endYear - startYear;
            Integer month = 0;
            if (endMonth >= startMonth) {
                month = endMonth - startMonth + 1;
            } else {
                month = 12 - startMonth + endMonth + 1;
            }
            value = year * 12 + month;
        } else if (period.equals(4)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
            String start = format.format(startTime);
            String end = format.format(endTime);
            Integer startYear = Integer.valueOf(start.substring(0, 4));
            Integer endYear = Integer.valueOf(end.substring(0, 4));
            Integer startMonth = Integer.valueOf(start.substring(4, 6));
            Integer endMonth = Integer.valueOf(end.substring(4, 6));
            Integer year = endYear - startYear;
            Integer month = 0;
            if (endMonth >= startMonth) {
                month = endMonth - startMonth + 1;
            } else {
                month = 12 - startMonth + endMonth + 1;
            }
            value = year * 12 + month;
            Double ceil = Math.ceil(value.doubleValue() / 3);
            value = ceil.intValue();
        } else if (period.equals(3)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
            String start = format.format(startTime);
            String end = format.format(endTime);
            Integer startYear = Integer.valueOf(start.substring(0, 4));
            Integer endYear = Integer.valueOf(end.substring(0, 4));
            value = endYear - startYear + 1;
        }

        return value;
    }

    //按照时间获取下某个周期的开始和结束时间
    //统计周期 1-日 2-月 3-季度 4-年
    public static Map<String, Date> periodNextDate(Date start, Integer period, Integer count) throws Exception {
        SimpleDateFormat slf = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
        Map<String, Date> map = new HashMap();
        if (period.equals(1)) {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(start);
            GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
            Date theDate = calendar.getTime();
            gcLast.setTime(theDate);
            gcLast.add(Calendar.DATE, count);
            Date time = gcLast.getTime();
            String format = df.format(time);
            if (count.equals(0)) {
                map.put("startDate", start);
            } else {
                Date startDate = slf.parse(format + " 00:00:00");
                map.put("startDate", startDate);
            }
            System.out.println("end:"+format);
            Date endDate = slf.parse(format + " 23:59:59");
            map.put("endDate", endDate);
        } else if (period.equals(2)) {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(start);
            Date theDate = calendar.getTime();
            if (count.equals(0)) {
                map.put("startDate", start);
            } else {
                // 第一天
                GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
                gcLast.setTime(theDate);
                gcLast.add(Calendar.MONTH, count);
                gcLast.set(Calendar.DATE,1);
                String dayFirst = df.format(gcLast.getTime());
                StringBuffer str = new StringBuffer().append(dayFirst);
                str.append(" 00:00:00");
                dayFirst = str.toString();
                System.out.println(dayFirst);
                map.put("startDate", slf.parse(dayFirst));
            }
            // 最后一天
            calendar.add(Calendar.MONTH, count + 1); // 加一个月
            calendar.set(Calendar.DATE, 1); // 设置为该月第一天
            calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
            String dayLast = df.format(calendar.getTime());
            StringBuffer endStr = new StringBuffer().append(dayLast);
            endStr.append(" 23:59:59");
            dayLast = endStr.toString();
            System.out.println(dayLast);
            map.put("endDate", slf.parse(dayLast));
        } else if (period.equals(3)) {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            String format = slf.format(start);
            int i = Integer.valueOf(format.substring(0, 4)) + count;
            if (count.equals(0)) {
                map.put("startDate", start);
            } else {
                // 第一天
                Calendar calendar = Calendar.getInstance();
                calendar.clear();
                calendar.set(Calendar.YEAR, i); // 加一年
                calendar.set(Calendar.DATE, 1);
                String dayFirst = df.format(calendar.getTime());
                StringBuffer str = new StringBuffer().append(dayFirst);
                str.append(" 00:00:00");
                String startStr = str.toString();
                System.out.println(startStr);
                map.put("startDate", slf.parse(startStr));
            }
            // 最后一天
            //calendar.add(Calendar.YEAR, count);
            //calendar.set(Calendar.DATE, 1); // 设置为该月第一天
            //calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天

            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, i);
            calendar.roll(Calendar.DAY_OF_YEAR, -1);
            Date date = calendar.getTime();
            StringBuffer endStr = new StringBuffer(df.format(date));
            endStr.append(" 23:59:59");
            String dayLast = endStr.toString();
            System.out.println(dayLast);
            map.put("endDate", slf.parse(dayLast));
        } else if (period.equals(4)) {
            //暂略
        }
        return map;
    }

    public static String changeDate(Date date, Integer period) {
        String value = null;
        SimpleDateFormat df = null;
        if (period.equals(1)) {
            df = new SimpleDateFormat("yyyy年MM月dd日");
            value = df.format(date);
        } else if (period.equals(2)) {
            df = new SimpleDateFormat("yyyy年MM月");
            value = df.format(date);
        }else if (period.equals(3)) {
            df = new SimpleDateFormat("yyyy年");
            value = df.format(date);
        }

        return value;
    }
}
