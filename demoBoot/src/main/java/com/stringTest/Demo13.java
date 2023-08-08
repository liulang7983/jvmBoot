package com.stringTest;

import com.util.SensitiveInfoUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ming.li
 * @date 2023/8/1 15:56
 */
public class Demo13 {
    // 身份证号码正则表达式
    private static final String ID_CARD_REGEX = "(\\d{4})\\d{10}(\\w{4})";

    public static void main(String[] args) {
        String str="我是雷锋ssss";
        System.out.println(SensitiveInfoUtils.desensitize(str,"4,-4"));

    }
}
