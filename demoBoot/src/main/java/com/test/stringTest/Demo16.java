package com.test.stringTest;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ming.li
 * @date 2023/10/10 11:44
 */
public class Demo16 {
    public static void main(String[] args) {
        System.out.println(checkRule("4,8s"));
    }
    public static String checkRule(String str){
        String regex="[+-]?[0-9]+?";
        String[] split = str.split(",");
        if (split.length==2){
            String s = split[0];
            String s1 = split[1];
            if (StringUtils.isNotBlank(s)){
                if (!s.matches(regex)||!s1.matches(regex)){
                    return "起始位置,结束位置只能输入整数";
                }
                Integer start = Integer.valueOf(split[0]);
                Integer end = Integer.valueOf(split[1]);
                if (start<0&&end>0){
                    return "不可初始位置为负,结束位置为正";
                }
                if ((start<0&&end<0&&start>end)||(start>0&&end>0&&start>end)){
                    return "同为正或者同为负时,初始位置不可大于结束位置";
                }
            }else {
                if (!s1.matches(regex)){
                    return "起始位置,结束位置只能输入整数";
                }
            }
        }else {
            if (!str.matches(regex)){
                return "起始位置,结束位置只能输入整数";
            }
        }
        return null;
    }
}
