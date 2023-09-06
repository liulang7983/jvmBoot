package lm.com.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

/**
 * @author ming.li
 * @date 2023/9/6 10:27
 */
public class FileUtils {


    /**
     * 读取ExeclMap.json内容
     * @return
     */
    public static JSONObject getExeclMap() {
        String strJson = FileUtils.readResourceFileToString("ExeclMap.json", "utf-8");
        //保证和json文件中的顺序一致
        LinkedHashMap<String, Object> json = JSON.parseObject(strJson, LinkedHashMap.class, Feature.OrderedField);
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.putAll(json);
        return jsonObject;
    }


    /**
     * 读取资源文件
     *
     * @param fileName:资源文件名
     * @return
     */
    public static String readResourceFileToString(String fileName, String cs) {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = FileUtils.class.getClassLoader().getResourceAsStream(fileName);
             InputStreamReader isr = new InputStreamReader(is, cs)) {
            try (BufferedReader reader = new BufferedReader(isr)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line.trim());
                }
            }
        } catch (IOException e) {
        }
        return sb.toString();
    }
}
