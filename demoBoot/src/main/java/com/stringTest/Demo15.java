package com.stringTest;

import java.io.File;

/**
 * @author ming.li
 * @date 2023/10/9 18:37
 */
public class Demo15 {
    public static void main(String[] args) {
        String str="157.pdf【1】";
        int start = str.indexOf("【");
        System.out.println(str.substring(0,start));
        String str1="2.jpg-10";
        int i = str1.lastIndexOf("-");
        System.out.println(str1.substring(0,i));
        String str2="2.pdf";
        System.out.println(str2.substring(str2.lastIndexOf(".")+1));
        System.out.println(str2.substring(0,str2.lastIndexOf("."))+"copy"+str2.substring(str2.lastIndexOf(".")));
        String s="张三历史司法考试倒计时抵扣积分发生大幅度法式复古";
        String s1=s.length()>20?s.substring(0,20):s;
        System.out.println(s1);
        String ss="ss"+ File.separatorChar+"ss.dd";
        System.out.println(ss);
        System.out.println(ss.substring(ss.lastIndexOf(File.separatorChar)+1));
    }


}
