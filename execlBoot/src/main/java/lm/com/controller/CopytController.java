package lm.com.controller;

import lm.com.bean.DownloadVo;
import lm.com.service.ExeclService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ming.li
 * @date 2023/9/6 10:15
 */
@RestController
@RequestMapping("copy")
public class CopytController {

    private static String filePath = "."+ File.separator;
    @Autowired
    private ExeclService execlService;

    @RequestMapping("copy")
    public void copy(HttpServletResponse res, DownloadVo downloadVo){
        String shellFile="b.js";
        ClassPathResource classPathResource = new ClassPathResource(shellFile);
        File file1 = new File(filePath + File.separator + shellFile);
        try (FileOutputStream outFileStream = FileUtils.openOutputStream(file1);
             InputStream inputStream = CopytController.class.getClassLoader().getResourceAsStream("js"+ File.separator+shellFile)) {
            IOUtils.copy(inputStream, outFileStream);
        } catch (IOException e) {

        }
    }

}
