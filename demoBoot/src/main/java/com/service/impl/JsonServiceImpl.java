package com.service.impl;

import com.model.json.HexOCRResult;
import com.service.JsonService;
import com.util.FileUtil;
import com.util.JsonUtil;
import org.springframework.stereotype.Service;

/**
 * @author ming.li
 * @date 2023/6/2 15:27
 */
@Service
public class JsonServiceImpl implements JsonService {
    @Override
    public HexOCRResult youtu() {
        String string = FileUtil.readResourceFileToString("C:\\Users\\ken\\Desktop\\日志\\识别结果\\优图1.json", "utf-8");
        HexOCRResult hexOCRResult = JsonUtil.jsonToHexOCRResult(string, "C:\\Users\\ken\\Desktop\\日志\\识别结果\\1.png");
        return hexOCRResult;
    }
}
