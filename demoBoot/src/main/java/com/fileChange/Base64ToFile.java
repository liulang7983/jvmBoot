package com.fileChange;

import com.alibaba.fastjson.JSONObject;
import com.util.Base64ToFileUtil;
import com.util.FileUtils;

/**
 * @author ming.li
 * @date 2023/11/8 10:52
 */
public class Base64ToFile {
    public static void main(String[] args) {
        String fileText = FileUtils.getFileText("C:\\liming\\change\\1.txt");
        System.out.println(fileText);
        JSONObject jsonObject = JSONObject.parseObject(fileText);

        Base64ToFileUtil.changeBaseToImage(jsonObject.getString("ss"),"C:\\liming\\change\\2.png");

    }
}
