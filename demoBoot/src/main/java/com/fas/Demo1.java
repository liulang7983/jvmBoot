package com.fas;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author ming.li
 * @date 2023/8/28 11:00
 */
public class Demo1 {
    public static void main(String[] args) {
        String uuid= UUID.randomUUID().toString().replace("-", "").toLowerCase();
        System.out.println(uuid);
        String fileName="123.png";
        String substring = fileName.substring(0,fileName.indexOf("."));
        System.out.println(substring);
        String text="1,242.08";
        text= text.replaceAll("[|lI\\[\\]\\{\\}\\【\\】]", "1");
        System.out.println(text);
        System.out.println(text.replaceAll("[^\\d.]", ""));
        BigDecimal bigDecimal = new BigDecimal("1,242.08");
        System.out.println(bigDecimal);
    }
}
