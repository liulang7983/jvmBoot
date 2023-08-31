package com.util;

import java.io.File;

/**
 * @author ming.li
 * @date 2023/8/14 18:07
 */
public class Base64Utils {

    public static String getImageBase64(String saveFullFileName) throws Exception {
        File imgFile = new File(saveFullFileName);
        imgFile = ImageTools.resizeImageSize(imgFile);
        return Base64Util.encode(OcrFileUtils.readFileByBytes(imgFile));
    }
}
