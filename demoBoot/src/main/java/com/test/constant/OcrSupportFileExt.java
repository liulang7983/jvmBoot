package com.test.constant;


/**
 * @author qinwu.zhu
 * @since 2020/4/21 9:43
 */
public enum OcrSupportFileExt {
    /**
     * pdf格式
     */
    FILE_EXTENSION_PDF("PDF"),
    FILE_EXTENSION_JPG("JPG"),
    FILE_EXTENSION_JPEG("JPEG"),
    FILE_EXTENSION_TIF("TIF"),
    FILE_EXTENSION_GIF("GIF"),
    FILE_EXTENSION_BMP("BMP"),
    FILE_EXTENSION_PNG("PNG"),
    FILE_EXTENSION_JFIF("JFIF");
    /**
     * 文件扩展名
     */
    private String fileExt;

    private OcrSupportFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getFileExt() {
        return this.fileExt;
    }
}
