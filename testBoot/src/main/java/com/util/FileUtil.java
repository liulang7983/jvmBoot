package com.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
    public static String readJsonFile() {
        try {
            // 读取JSON文件内容
            return new String(Files.readAllBytes(Paths.get("C:\\Users\\14307\\Desktop\\日志\\mi\\一页两表\\识别.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String removeSpecialCharacters(String text) {
        if(StringUtils.isBlank(text)){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<text.length();i++){
            char c = text.charAt(i);
            if(c>=65 && c<=90 ){
                sb.append(c);
            }else if(c>=97 && c<=122 ){
                sb.append(c);
            }else if(c>='\u4e00' && c<='\u9fa5'){
                sb.append(c);
            }
        }
        return sb.toString();

    }
    /**
     * 读取资源文件
     *
     * @param fileName:资源文件名
     * @return
     */
    public static String readResourceFileToString(String fileName, String cs) {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
             InputStreamReader isr = new InputStreamReader(is, cs)) {
            try (BufferedReader reader = new BufferedReader(isr)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line.trim());
                }
            }
        } catch (IOException e) {
            logger.error("获取文件出错:", e);
        }
        return sb.toString();
    }
}
