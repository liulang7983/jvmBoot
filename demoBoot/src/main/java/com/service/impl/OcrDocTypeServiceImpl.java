package com.service.impl;

import com.bean.ApiResult;
import com.bean.OcrDocType;
import com.dao.OcrDocTypeMapper;
import com.service.OcrDocTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ming.li
 * @date 2023/7/19 11:26
 */
@Service
public class OcrDocTypeServiceImpl implements OcrDocTypeService {

    @Autowired(required = false)
    private OcrDocTypeMapper ocrDocTypeMapper;
    @Override
    public ApiResult selectDocTypeTree() {
        List<OcrDocType> ocrDocTypes = ocrDocTypeMapper.selectList();
        List<OcrDocType> collect = ocrDocTypes.stream().filter(ocrDocType -> {
            if (ocrDocType.getParentTypeId() == null) {
                ocrDocType.setParentTypeId("");
            }
            return true;
        }).collect(Collectors.toList());
        for (OcrDocType docType:ocrDocTypes){
            if (docType.getParentTypeId()==null){
                docType.setParentTypeId("");
            }
        }
        return new ApiResult(ocrDocTypes);
    }
}
