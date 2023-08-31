package com.pdfTest;

import com.util.Base64Utils;

/**
 * @author ming.li
 * @date 2023/8/14 18:15
 */
public class Demo1 {
    public static void main(String[] args) throws Exception {
        //String imageBase64 = Base64Utils.getImageBase64("C:\\Users\\ken\\Desktop\\日志\\各类图片\\空运单.pdf");
        String imageBase64 = Base64Utils.getImageBase64("C:\\liming\\脱敏\\勋的出入境记录.pdf");
        System.out.println(imageBase64);
    }
}
