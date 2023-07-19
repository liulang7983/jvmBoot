package com.bean;


public class OcrDocType {
    /**
     * 识别类型id
     */
    private String typeId;

    /**
     * 证件类型名
     */
    private String docTypeName;


    /**
     * 调用识别引擎类型
     */
    private String ocrEngineType;


    /**
     * '父级文档类型ID'
     */
    private String parentTypeId;

    /**
     * '排列顺序号'
     */
    private Integer sortNo;

    /**
     * 显示树顺序字符串
     */
    private String seqNo;
    /**
     * 表格引擎识别时列定义'
     */

    private String tableTitle;
    //识别类型
    private String ocrOriginType;
    //上级类型名称
    private String parentTypeName;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDocTypeName() {
        return docTypeName;
    }

    public void setDocTypeName(String docTypeName) {
        this.docTypeName = docTypeName;
    }

    public String getOcrEngineType() {
        return ocrEngineType;
    }

    public void setOcrEngineType(String ocrEngineType) {
        this.ocrEngineType = ocrEngineType;
    }

    public String getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(String parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getTableTitle() {
        return tableTitle;
    }

    public void setTableTitle(String tableTitle) {
        this.tableTitle = tableTitle;
    }

    public String getOcrOriginType() {
        return ocrOriginType;
    }

    public void setOcrOriginType(String ocrOriginType) {
        this.ocrOriginType = ocrOriginType;
    }

    public String getParentTypeName() {
        return parentTypeName;
    }

    public void setParentTypeName(String parentTypeName) {
        this.parentTypeName = parentTypeName;
    }
}
