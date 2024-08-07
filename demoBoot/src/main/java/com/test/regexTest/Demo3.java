package com.test.regexTest;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Demo3 {
    public static void main(String[] args) {
        String s=".*单位.*";
        String[] split = s.split(",");
        Integer a=null;
        String text="2344.000DSDS";
        text=text.replaceAll("[^\\d.]", "");
        System.out.println(text);
        text=getRightNumber(text);
        System.out.println(text);
        System.out.println(calMatchAny(split,"单位"));
        BigDecimal b=new BigDecimal(text).setScale(0,BigDecimal.ROUND_HALF_UP);
        System.out.println(b.compareTo(new BigDecimal(text)));
    }
    public static int calMatchAny(String[] regArray,String text){
        if(regArray==null||regArray.length==0|| StringUtils.isBlank(text)){
            return -1;
        }

        for (int i = 0; i < regArray.length; i++) {
            boolean isMatch =  Pattern.matches(regArray[i],text);
            if(isMatch){
                return i;
            }
        }
        return -1;
    }
    public static String getRightNumber(String strNum) {
        if (strNum == null || strNum.trim().length() == 0) {
            return "0.00";
        }
        //处理小数点位
        strNum = removePoint(strNum);
        //TODO 金额列金额处理

        return strNum;
    }
    public static String removePoint(String strNum) {
        int length = strNum.length();
        if (length <= 0|| ".00".equals(strNum)) {
            return "0.00";
        }
        if (length > 1) {
            //首尾为小数点，去除
            if (strNum.charAt(0) == '.') {
                strNum = strNum.substring(1);
            }
            if (strNum.lastIndexOf(".") == length - 1) {
                strNum = strNum.substring(0, length - 1);
            }
        }
        //存在多个小数点以及末位小数点后有超过两位小数处理
        int pointNum = StringUtils.countMatches(strNum, ".");
        if (pointNum > 1) {
            int lastPointIndex = strNum.lastIndexOf(".");
            String strNum1 = strNum.substring(0, lastPointIndex).replace(".", "");
            String strNum2 = strNum.substring(lastPointIndex);
            strNum = strNum1 + strNum2;
        }
        pointNum = StringUtils.countMatches(strNum, ".");
        if (pointNum == 1) {
            int gap = strNum.length() - 1 - strNum.lastIndexOf(".");
            if (gap > 2) {
                strNum = strNum.substring(0, strNum.lastIndexOf(".") + 3);
            }
            if (gap < 2) {
                strNum = strNum + "0";
            }
        }
        if (pointNum < 1) {
            strNum = strNum + ".00";
        }
        //TODO
        return strNum;
    }
}
