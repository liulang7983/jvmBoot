package com.test.hehe;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @Author ming.li
 * @Date 2025/1/7 14:51
 * @Version 1.0
 */
public class CopyDemo {
    public static void main(String[] args) {
        String fmsFilePath="C:\\Users\\14307\\Desktop\\pdf查重\\35\\矢量\\矢量封装 - 副本.json";
        String filePath="C:\\Users\\14307\\Desktop\\pdf查重\\35\\矢量\\矢量封装1.json";
        try {
            Files.copy(Paths.get(fmsFilePath), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
