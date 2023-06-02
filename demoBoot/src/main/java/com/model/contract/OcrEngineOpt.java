package com.model.contract;

/**
 * 合同识别引擎枚举
 */
public enum OcrEngineOpt {
    /**
     * 没有实现，暂时冗余
     */
    ABBY,
    /**
     * 原件和扫描件都使用hexocr引擎解析
     */
    HEX_OCR,

    /**
     * 原件pdf解析，扫描件hexocr引擎解析
     */
    PDF_PARSE_HEX,

    /**
     * 合合引擎
     */
    INTSIG,

    /**
     * 表格引擎
     */
    TABLE
}
