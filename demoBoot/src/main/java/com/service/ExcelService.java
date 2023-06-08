package com.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ming.li
 * @date 2023/5/11 19:18
 */
public interface ExcelService {
    String upload( MultipartFile file) throws IOException;

    void downloadExcel(HttpServletResponse response);
}
