package com.controller;

import com.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ming.li
 * @date 2023/5/11 19:15
 */
@RestController
@RequestMapping("/execl")
public class ExcelController {

    @Autowired
    private ExcelService excelService;
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        return excelService.upload(file);
    }
}
