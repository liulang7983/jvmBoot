package com.util;

import com.thoughtworks.xstream.core.BaseException;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

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

    /**
     * 将base64编码的图片转换为图片存储
     * @param base 图片的base64编码
     * @param exportImgPath 图片的导出路径 2.png
     */
    public static void changeBaseToImage(String base, String exportImgPath) throws BaseException {
        if (StringUtils.isEmpty(base)) {
            return;
        }
        //创建文件目录
        if (!isExist(exportImgPath)) {
            File dir = new File(exportImgPath);
            //创建文件目录
            dir.getParentFile().mkdirs();
        }
        byte[] bytes = Base64.getDecoder().decode(base);
        File file = new File(exportImgPath);
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(bytes);
        } catch (IOException e) {
            System.out.println("base64ToImage 错误 "+e);
        }
    }

    /**
     * 判断此目录是否存在
     *
     * @param path
     * @return
     */
    public static boolean isExist(String path) {
        File file = new File(path);
        return file.exists();
    }
}
