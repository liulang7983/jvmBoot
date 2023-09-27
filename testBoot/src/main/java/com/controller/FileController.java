package com.controller;

import ch.qos.logback.core.util.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author ming.li
 * @date 2023/9/27 17:28
 */
@RestController
@RequestMapping("file")
public class FileController {
    @RequestMapping("getFile")
    public void getInvoice(HttpServletResponse res){
        String zipPath="C:\\liming\\发票\\价税合计为空.png";
        String filename="价税合计为空.png";
        //zip压缩文件保存的路径
        BufferedInputStream bis = null;
        ServletOutputStream os = null;
        try {
            res.reset();
            filename = java.net.URLEncoder.encode(filename,"UTF-8").replace("+", "%20");
            res.setHeader("Content-disposition", "attachment; filename=" + filename);
            res.setContentType("application/octet-stream");
            /*res.setHeader("Content-Disposition", "attachment;fileName=" +
                    new String(zipName.getBytes("gbk"), "iso8859-1"));*/
            bis = new BufferedInputStream(new FileInputStream(zipPath));
            os = res.getOutputStream();
            int len;
            byte[] bytes = new byte[1024 * 2];
            while ((len = bis.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("下载异常!");
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
