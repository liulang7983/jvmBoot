package com.intTest;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class Demo5 {

    public static void main(String[] args) {
        String text1 = "00.123";
        System.out.println(removePoint(text1));
    }
    public static String removePoint(String strNum) {
        int length = strNum.length();
        if (length <= 0|| ".00".equals(strNum)) {
            return "0.00";
        }
        if (length > 1) {
            //首尾为小数点，去除
            if (strNum.charAt(0) == '.') {
                strNum = "0"+strNum;
                length++;
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
        strNum = new BigDecimal(strNum).toString();
        //TODO
        return strNum;
    }
}
