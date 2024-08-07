package com.test.timeTest;

import java.io.File;
import java.util.Date;

/**
 * @Author ming.li
 * @Date 2024/5/20 16:00
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args)throws Exception {
        Date date = new Date();
        Thread.sleep(1000);
        Date date1 = new Date();
        System.out.println(date.getTime()-date1.getTime());

        String ss="ss.pdf";
        String substring = ss.substring(0,ss.lastIndexOf("."));
        System.out.println(substring);
        File file = new File("C:\\liming\\租赁物\\报告\\13179102001574831697.pdf");
        System.out.println(file.getName());
        String s1="[^\\d.-]";
        String ss1="-x1";
        ss1=ss1.replaceAll(s1,"");
        System.out.println(ss1);
    }
}
