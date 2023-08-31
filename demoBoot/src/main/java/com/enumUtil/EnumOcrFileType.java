package com.enumUtil;


import java.util.Arrays;
import java.util.List;

/**
 * 识别文件类型
 * @Author zs.tang
 * @create 2021/6/24 11:08
 */
public enum EnumOcrFileType {

    /**
     * txt 文件类型
     */
    OCR_FILE_TYPE_TXT("txt"),

    /**
     * xml 文件类型
     */
    OCR_FILE_TYPE_XML("xml"),

    /**
     * xls 文件类型
     */
    OCR_FILE_TYPE_XLS("xls"),

    /**
     * xlsx 文件类型
     */
    OCR_FILE_TYPE_XLSX("xlsx"),

    /**
     * jpg 文件类型
     */
    OCR_FILE_TYPE_JPG("jpg"),

    /**
     * jpeg 文件类型
     */
    OCR_FILE_TYPE_JPEG("jpeg"),

    /**
     * bmp 文件类型
     */
    OCR_FILE_TYPE_BMP("bmp"),

    /**
     * png 文件类型
     */
    OCR_FILE_TYPE_PNG("png"),

    /**
     * json 文件类型
     */
    OCR_FILE_TYPE_JSON("json"),

    /**
     * tif 文件类型
     */
    OCR_FILE_TYPE_TIF("tif"),

    /**
     * pdf 文件类型
     */
    OCR_FILE_TYPE_PDF("pdf")
    ;

    /**
     * 文件类型
     */
    private String fileType;

    private static final List<EnumOcrFileType> exNameList = Arrays.asList(OCR_FILE_TYPE_XML, OCR_FILE_TYPE_XLS, OCR_FILE_TYPE_XLSX,OCR_FILE_TYPE_JSON);

    EnumOcrFileType(String fileType){
        this.fileType = fileType;
    }

    public static EnumOcrFileType getByFileType(String fileType){
        for (EnumOcrFileType obj : EnumOcrFileType.values()) {
            if(obj.getFileType().equalsIgnoreCase(fileType)) {
                return obj;
            }
        }
        return null;
    }

    public static EnumOcrFileType getByFileTypeDefault(String fileType){
        for (EnumOcrFileType obj : exNameList) {
            if(obj.getFileType().equalsIgnoreCase(fileType)) {
                return obj;
            }
        }
        return OCR_FILE_TYPE_JPG;
    }

    public String getFileType() {
        return fileType;
    }

}
