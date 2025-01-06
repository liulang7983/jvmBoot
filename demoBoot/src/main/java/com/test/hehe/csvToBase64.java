package com.test.hehe;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author ming.li
 * @Date 2025/1/6 15:56
 * @Version 1.0
 */
public class csvToBase64 {
    public static void main(String[] args) {
        byte[] fileBytes = getFileBytes("C:\\Users\\14307\\Desktop\\pdf查重\\1\\01004acf0.csv");
        String csvBase64Str = java.util.Base64.getEncoder().encodeToString(fileBytes);
        System.out.println(csvBase64Str);
    }
    public static byte[] getFileBytes(String imgFile) {
        byte[] data = null;
        /*
        读取文件字节数组
         */
        try (InputStream in = new FileInputStream(imgFile)) {
            data = new byte[in.available()];
            while (in.read(data) > 0) {
                /*
                 ...
                 */
            }
        } catch (IOException e) {
        }
        return data;
    }
}
