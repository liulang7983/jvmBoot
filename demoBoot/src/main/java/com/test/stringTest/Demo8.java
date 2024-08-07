package com.test.stringTest;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ming.li
 * @date 2023/6/29 17:42
 */
public class Demo8 {
    public static void main(String[] args) {
        String str1="sss";
        String str2=null;
        if (str1.trim().equals("sss")){
            System.out.println("str1是sss");
        }else {
            System.out.println("str1不是sss");
        }

        if (StringUtils.isNotBlank(str2)&&str2.trim().equals("sss")){
            System.out.println("str2是sss");
        }else {
            System.out.println("str2不是sss");
        }
    }
}
