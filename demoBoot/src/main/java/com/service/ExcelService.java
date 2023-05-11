package com.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ming.li
 * @date 2023/5/11 19:18
 */
public interface ExcelService {
    String upload( MultipartFile file);
}
