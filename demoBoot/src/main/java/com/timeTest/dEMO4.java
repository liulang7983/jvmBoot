package com.timeTest;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ming.li
 * @date 2023/8/17 19:44
 */
public class dEMO4 {
    public static void main(String[] args) {
        String endTime="2023-08-31";
        try {
            if (!StringUtils.isEmpty(endTime)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(endTime);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DATE, 1);
                Date time = calendar.getTime();
                endTime = sdf.format(time);
                System.out.println(endTime);
            }
        } catch (Exception e) {

        }
    }
}
