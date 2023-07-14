package com.controller;

import org.ofdrw.converter.ConvertHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author ming.li
 * @date 2023/7/13 15:26
 */
@RestController
@RequestMapping("odf")
public class OdfToPdfController {
    //解析execl
    @RequestMapping("/topdf")
    public void topdf() throws IOException {
        String fmsFileFullPath="C:\\Users\\ken\\Desktop\\日志\\转\\27.ofd";
        String newFullPath= fmsFileFullPath.substring(0,fmsFileFullPath.lastIndexOf(".")+1)+"pdf";
        ConvertHelper.toPdf(new FileInputStream(fmsFileFullPath),new File(newFullPath));
    }
}
