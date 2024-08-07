package com.util;

import com.test.constant.OcrSupportFileExt;


import java.util.UUID;

public class OcrUtil {


    public static boolean isPdf(String fileName) {
        return fileName.endsWith("pdf") || fileName.endsWith("PDF");
    }


    /**
     * 判断是否为PDF文件
     *
     * @param extName:文件扩展名
     * @return boolean：是否
     */
    public static boolean isSupportTif(String extName) {
        return OcrSupportFileExt.FILE_EXTENSION_TIF.getFileExt().equalsIgnoreCase(extName);
    }

    /**
     * 判断是否为PDF文件
     *
     * @param extName:文件扩展名
     * @return boolean：是否
     */
    public static boolean isSupportPdf(String extName) {
        return OcrSupportFileExt.FILE_EXTENSION_PDF.getFileExt().equalsIgnoreCase(extName);
    }

    /**
     * 判断是否为支持的图片格式
     *
     * @param extName:文件扩展名
     * @return boolean：是否
     */
    public static boolean isSupportImageTypes(String extName) {
        if (OcrSupportFileExt.FILE_EXTENSION_BMP.getFileExt().equalsIgnoreCase(extName) ||
                OcrSupportFileExt.FILE_EXTENSION_PNG.getFileExt().equalsIgnoreCase(extName) ||
                OcrSupportFileExt.FILE_EXTENSION_JPEG.getFileExt().equalsIgnoreCase(extName) ||
                OcrSupportFileExt.FILE_EXTENSION_JPG.getFileExt().equalsIgnoreCase(extName)) {
            return true;
        }
        return false;

    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
