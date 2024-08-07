package com.test.fileTest;

import java.io.File;

/**
 * @Author ming.li
 * @Date 2024/4/2 16:31
 * @Version 1.0
 */
public class Demo4 {
    public static void main(String[] args) {
        String str="/mnt/data/exdoc/medical/20240402/922010/602025b850/1.jpg";
        int i = str.lastIndexOf("/");
        System.out.println(i);
        String ss=str.substring(0,i)+File.separator+"formatted"+str.substring(i);
        System.out.println(ss);
        String sss="C:\\Users\\14307\\Desktop\\日志\\mi\\fymx0.png";
        System.out.println(new File(sss).exists());
    }
}
