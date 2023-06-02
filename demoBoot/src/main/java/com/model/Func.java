package com.model;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @author ming.li
 * @date 2023/6/2 15:41
 */
public class Func {
    /**
     * 读取文本文件
     *
     * @param filePath 文本文件路径
     * @return 返回文本内容
     */
    public static StringBuilder readTextFile(String filePath, String charsetName) {
        StringBuilder sbText = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(filePath);
             InputStreamReader isr = new InputStreamReader(fis, charsetName);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                sbText.append(line);
            }
        } catch (Exception e) {
            System.out.println("读取文件失败！");
        }
        return sbText;
    }

    /**
     * 按文件名称顺序排序
     *
     * @param listFile 文件名称列表
     */
    public static void fileNameOrderBy(List<File> listFile) {
        //order by文件名称列表
        Collections.sort(listFile, (f1, f2) -> {
            String name1 = f1.getName();
            String name1WithoutExt = name1.substring(0, name1.lastIndexOf('.'));
            Integer n1 = Integer.valueOf(name1WithoutExt);
            String name2 = f2.getName();
            String name2WithoutExt = name2.substring(0, name2.lastIndexOf('.'));
            Integer n2 = Integer.valueOf(name2WithoutExt);
            return n1.compareTo(n2);
        });
    }

    /**
     * JSON反序列化对象
     *
     * @param json JSON字符串
     * @param type 反序列化对象类型
     * @return 对象实例
     */
    public static <T> T jsonString2Object(String json, Class<T> type) {
        return JSON.parseObject(json, type);
    }

    /**
     * 从资源目录返回指定资源文件的内容
     *
     * @param resourceName
     * @return
     */
    public static String returnResourceFileText(String resourceName) {
        StringBuilder sb = new StringBuilder();
        try {
            final InputStream is = Func.class.getClassLoader().getResourceAsStream(resourceName);
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);//或者utf-8
            try (BufferedReader reader = new BufferedReader(isr)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("returnResourceFileText 错误 {}");
        }
        return sb.toString();
    }

}

