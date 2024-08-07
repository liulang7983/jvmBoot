package com.test.stringTest;

import com.util.SensitiveInfoUtils;

/**
 * @author ming.li
 * @date 2023/8/1 15:56
 */
public class Demo13 {
    // 身份证号码正则表达式
    private static final String ID_CARD_REGEX = "(\\d{4})\\d{10}(\\w{4})";

    public static void main(String[] args) {
        String str="我是雷锋ssss";
        System.out.println(SensitiveInfoUtils.desensitize(str,"3"));
        System.out.println(SensitiveInfoUtils.desensitize(str,",7"));
        System.out.println(SensitiveInfoUtils.desensitize(str,"2,4"));
        System.out.println(SensitiveInfoUtils.desensitize(str,"2,40"));
        System.out.println(SensitiveInfoUtils.desensitize(str,"2,-2"));
        System.out.println(SensitiveInfoUtils.desensitize(str,"2,-40"));
        System.out.println(SensitiveInfoUtils.desensitize(str,"-3,-2"));
    }
}
