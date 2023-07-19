package com.controller;

import com.bean.ApiResult;
import com.service.OcrDocTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ming.li
 * @date 2023/7/19 11:23
 */
@Api(tags = "文档类型及科目控制器")
@RestController
@RequestMapping("ocr/doctype")
public class OcrDocTypeController {

    @Autowired
    private OcrDocTypeService ocrDocTypeService;

    @ApiOperation(value = "左侧类型树展示")
    @PostMapping(value = "/doctypetree")
    public ApiResult showTypeTree() {
        return ocrDocTypeService.selectDocTypeTree();
    }
}
