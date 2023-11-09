package com.util;

import java.io.File;

/**
 * @author ming.li
 * @date 2023/11/8 11:33
 */
public class AngelUtil {
    /**
     * 按角度旋转图片
     *
     * @param imagePath:文件名称
     * @param angel：角度
     * @return boolean：成功失败
     */
    public static boolean rotateImageByAngel(String imagePath, int angel) {
        try {
            ImageTools.rotateImage(new File(imagePath), angel);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
