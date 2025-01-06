package com.test.fileChange;

import com.alibaba.fastjson.JSONObject;
import com.util.FileUtils;
import com.util.FileToBase64Util;

import java.io.File;
import java.io.IOException;

/**
 * @author ming.li
 * @date 2023/11/8 10:41
 */
public class fileToBase64 {

    public static void main(String[] args) throws IOException {
        String s = FileToBase64Util.GetBase64FromFile(new File("C:\\Users\\14307\\Desktop\\pdf查重\\报告\\01004a690.jpg"));
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("ss",s);
        FileUtils.writeTextFile1("C:\\Users\\14307\\Desktop\\pdf查重\\报告\\1.txt",jsonObject.toString());
    }

}

