package com.odfToPdf;

import com.util.IasFileUtils;
import org.ofdrw.converter.ConvertHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author ming.li
 * @date 2023/7/13 15:17
 */
public class demo3 {
    public static void main(String[] args) throws FileNotFoundException {
        String fmsFileFullPath="C:\\Users\\ken\\Desktop\\日志\\转\\27.ofd";
        String newFullPath= fmsFileFullPath.substring(0,fmsFileFullPath.lastIndexOf(".")+1)+"pdf";
        ConvertHelper.toPdf(new FileInputStream(fmsFileFullPath),new File(newFullPath));
        Integer pdfPageSize = IasFileUtils.getPdfPageSize(newFullPath);//获取pdf页数
    }
}
