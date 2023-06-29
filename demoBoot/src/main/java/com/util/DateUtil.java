package com.util;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
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
            Long l = endTime.getTime() - startTime.getTime();
            Long v1 = l / (1000 * 3600 * 24);
            value = v1.intValue();
            value++;
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
    //统计周期 1-日 2-月 3-年
    public static Map<String, String> periodNextDate(Date start,Date endTime, Integer period, Integer count){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, String> map = new HashMap();
        if (period.equals(1)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(start);
            GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
            Date theDate = calendar.getTime();
            gcLast.setTime(theDate);
            gcLast.add(Calendar.DATE, count);
            Date time = gcLast.getTime();
            String format = df.format(time);
            map.put("startDate", format + " 00:00:00");
            map.put("endDate", format + " 23:59:59");
        } else if (period.equals(2)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(start);
            Date theDate = calendar.getTime();
            if (count.equals(0)) {
                map.put("startDate", df.format(start)+" 00:00:00");
            } else {
                // 第一天
                GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
                gcLast.setTime(theDate);
                gcLast.add(Calendar.MONTH, count);
                gcLast.set(Calendar.DATE, 1);
                String dayFirst = df.format(gcLast.getTime());
                StringBuffer str = new StringBuffer().append(dayFirst);
                str.append(" 00:00:00");
                dayFirst = str.toString();
                map.put("startDate", dayFirst);
            }
            // 最后一天
            calendar.add(Calendar.MONTH, count + 1); // 加一个月
            calendar.set(Calendar.DATE, 1); // 设置为该月第一天
            calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
            String dayLast = df.format(calendar.getTime());
            StringBuffer endStr = new StringBuffer().append(dayLast);
            String dayEnd = endStr.append(" 23:59:59").toString();
            try {
                Date parse = df.parse(endStr.toString());
                if (parse.after(endTime)){
                    dayEnd =df.format(endTime)+" 23:59:59";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            map.put("endDate", dayEnd);
        } else if (period.equals(3)) {
            String format = df.format(start);
            int i = Integer.valueOf(format.substring(0, 4)) + count;
            if (count.equals(0)) {
                map.put("startDate", df.format(start)+" 00:00:00");
            } else {
                // 第一天
                Calendar calendar = Calendar.getInstance();
                calendar.clear();
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.DATE, 1);
                String dayFirst = df.format(calendar.getTime());
                StringBuffer str = new StringBuffer().append(dayFirst);
                str.append(" 00:00:00");
                String startStr = str.toString();
                map.put("startDate", startStr);
            }
            // 最后一天
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, i);
            calendar.roll(Calendar.DAY_OF_YEAR, -1);
            String dayLast = df.format(calendar.getTime());
            StringBuffer endStr = new StringBuffer().append(dayLast);
            String dayEnd = endStr.append(" 23:59:59").toString();
            try {
                Date parse = df.parse(endStr.toString());
                if (parse.after(endTime)){
                    dayEnd =df.format(endTime)+" 23:59:59";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            map.put("endDate", dayEnd);
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

    public static String getYearMonthDay(String val, String patten) {
        if (StringUtils.hasText(val)) {
            try {
                String[] dateFormats = new String[]{"yyyy-MM-dd", "yyyy年MM月dd日", "yyyy/MM/dd", "yyyyMMdd", "yyyy.MM.dd", "yyyy年M月dd日","yyyy-MM-dd hh:mm:ss","yyyyMMdd hh:mm:ss"};
                Date date = DateUtils.parseDate(val, dateFormats);
                String val2 = format(date, patten);
                return val2;
            } catch (Exception var5) {
                LogManager.getLogger(DateUtil.class).error("getYearMonth exception:{}", var5);
            }
        }
        return "";
    }
    public static String format(Date date, String patten) {
        DateFormat format2 = new SimpleDateFormat(patten);
        return format2.format(date);
    }

    public static Integer intervalDay(Date start,Date end){
        Integer value = null;
        SimpleDateFormat slf = new SimpleDateFormat("yyyy-MM-dd");
        String startStr = slf.format(start);
        String endStr = slf.format(end);
        try {
            Date startDate = slf.parse(startStr);
            Date endDate = slf.parse(endStr);
            Long l = endDate.getTime() - startDate.getTime();
            Long v1 = l / (1000 * 3600 * 24);
            value = v1.intValue();
            value++;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return value;
    }
}
