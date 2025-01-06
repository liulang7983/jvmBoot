package com.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author ming.li
 * @Date 2025/1/6 16:41
 * @Version 1.0
 */
public class NlpRegFile {

    /*
     主键ID
     */
    private Long nlpRfId;
    /*
     登记信息表主键ID
     */
    private Long nlpRegId;
    /*
     页码
     */
    private Integer pageNo;
    /*
     影像文件
     */
    private String picFileId;
    /*
     OCR识别后标准格式文件
     */
    private String jsonFileId;
    /*
    OCR识别后转csv文件
     */
    private String csvFileId;
    /*
     附件类型 1-中登网(PDF) 2-中登网(附件) 3-企查查
     */
    private Integer nlpRfType;
    /*
     ocr识别文件类型: 0为文本，1为表格
     */
    private Integer ocrFileType;
    /*
     识别状态:0-成功 1--失败
     */
    private Integer nlpRfStatus;

    /**
     * pdf文件名，同一份pdf不同页，名称相同
     */
    private String pdfName;

    /**
     * pdf附件，表头信息(若有)
     */
    private String tableHead;


    /*
     错误信息
     */
    private String errMsg;
    /*
     租户ID
     */
    private String tenantId;
    /*
     组织ID
     */
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    /*
    创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date crtTs;
    /*
     创建者
     */
    private String crtUser;
    /*
     创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date udtTs;
    /*
     更新人员
     */
    private String udtUser;
    /*
     重复结果数量
     */
    private Integer existDupCount;

    /*
     关联中登租赁物
     */
    //private List<NlpZdLease> zdLeases;

    /**
     * 是否含有表头
     */
    private boolean hasTableHead;

    public Long getNlpRfId() {
        return nlpRfId;
    }

    public void setNlpRfId(Long nlpRfId) {
        this.nlpRfId = nlpRfId;
    }

    public Long getNlpRegId() {
        return nlpRegId;
    }

    public void setNlpRegId(Long nlpRegId) {
        this.nlpRegId = nlpRegId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getPicFileId() {
        return picFileId;
    }

    public void setPicFileId(String picFileId) {
        this.picFileId = picFileId;
    }

    public String getJsonFileId() {
        return jsonFileId;
    }

    public void setJsonFileId(String jsonFileId) {
        this.jsonFileId = jsonFileId;
    }

    public String getCsvFileId() {
        return csvFileId;
    }

    public void setCsvFileId(String csvFileId) {
        this.csvFileId = csvFileId;
    }

    public Integer getNlpRfType() {
        return nlpRfType;
    }

    public void setNlpRfType(Integer nlpRfType) {
        this.nlpRfType = nlpRfType;
    }

    public Integer getOcrFileType() {
        return ocrFileType;
    }

    public void setOcrFileType(Integer ocrFileType) {
        this.ocrFileType = ocrFileType;
    }

    public Integer getNlpRfStatus() {
        return nlpRfStatus;
    }

    public void setNlpRfStatus(Integer nlpRfStatus) {
        this.nlpRfStatus = nlpRfStatus;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public String getTableHead() {
        return tableHead;
    }

    public void setTableHead(String tableHead) {
        this.tableHead = tableHead;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Date getCrtTs() {
        return crtTs;
    }

    public void setCrtTs(Date crtTs) {
        this.crtTs = crtTs;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    public Date getUdtTs() {
        return udtTs;
    }

    public void setUdtTs(Date udtTs) {
        this.udtTs = udtTs;
    }

    public String getUdtUser() {
        return udtUser;
    }

    public void setUdtUser(String udtUser) {
        this.udtUser = udtUser;
    }

    public Integer getExistDupCount() {
        return existDupCount;
    }

    public void setExistDupCount(Integer existDupCount) {
        this.existDupCount = existDupCount;
    }

    public boolean isHasTableHead() {
        return hasTableHead;
    }

    public void setHasTableHead(boolean hasTableHead) {
        this.hasTableHead = hasTableHead;
    }
}
