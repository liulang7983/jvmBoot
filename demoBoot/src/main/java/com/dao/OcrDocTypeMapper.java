package com.dao;

import com.bean.OcrDocType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ming.li
 * @date 2023/7/19 11:27
 */
@Mapper
public interface OcrDocTypeMapper {
    /**
     * 查询证件照类型信息
     */
    List<OcrDocType> selectList();
}
