package com.util;

/**
 * @author ming.li
 * @date 2023/6/9 10:56
 */
public class StringUtil {
    //某个字符后保留几位小数
    public static String repairStr(String str,String patten,Integer count){
        int index = str.lastIndexOf(patten);
        if (index==-1){
            str+=patten;
            for (int i = 0; i <count ; i++) {
                str+=0;
            }
        }else {
            int length = str.substring(index+1).length();
            int i1 = count - length;
            if (i1>0){
                for (int i = 0; i < i1; i++) {
                    str+=0;
                }
            }else {
                str=str.substring(0,str.length()+i1);
            }
        }
        return str;
    }
    //判断字符串是不是数组组成
    public static boolean isNumeric2(String str) {
        return str != null && str.matches("-?\\d+(\\.\\d+)?");
    }

    //去掉特殊字符
    public static String removeChar(String appendixName, String[] char_regix) {
        for (String s : char_regix) {
            appendixName = appendixName.replace(s, "");
        }
        return appendixName;
    }

}
