package com.regexTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author ming.li
 * @Date 2024/6/11 10:54
 * 去掉特殊字符
 * @Version 1.0
 */
public class Demo6 {
    static String  regEx = "[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， ·、？]";
    static String aa = "";//这里是将特殊字符换为aa字符串,""代表直接去掉

    public static void main(String[] args) {
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher("测试来着，赛风·到付·“，、！扫扥看");//这里把想要替换的字符串传进来
        String newString = m.replaceAll(aa).trim();//将替换后的字符串存在变量newString中
        System.out.println("newString = " + newString);
        String str = "测试来着，赛风·到付·“，、！扫扥看";
        String newString1 = str.replaceAll(regEx, aa);//不想保留原来的字符串可以直接写成 “str = str.replaceAll(regEX,aa);”
        str.replaceAll(regEx, aa);
        System.out.println("newString = " + newString1);
    }

	/*
	输出结果：
	newString = 测试来着赛风到付扫扥看
	 */



    // 方法二
    public void test2() {

    }
	/*
	输出结果：
	newString = 测试来着赛风到付扫扥看
	 */
}
