package com.model;

import lombok.Data;

import java.util.Date;

@Data
public class Statistics {

    private String userName;

    private String date;

    private String department;

    private int invoiceTotal;

    private int ocrSuccessNum;

    private int ocrFailNum;

    private int checkTrueNum;

    private int checkFailNum;

    private int verificationTrueNum;

    private int verificationFailNum;

    private String dataSource;

    private String invoiceCode;

    private String invoiceNumber;

    private String invoiceType;

    private String fileName;

    private String checkCount;

    private Date startTime;

    private Date endTime;

    private String remarkId;

    private String corporationType;

    private String corporationName;
    private int verifyTrueSum;

    private String dateDescription;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(int invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public int getOcrSuccessNum() {
        return ocrSuccessNum;
    }

    public void setOcrSuccessNum(int ocrSuccessNum) {
        this.ocrSuccessNum = ocrSuccessNum;
    }

    public int getOcrFailNum() {
        return ocrFailNum;
    }

    public void setOcrFailNum(int ocrFailNum) {
        this.ocrFailNum = ocrFailNum;
    }

    public int getCheckTrueNum() {
        return checkTrueNum;
    }

    public void setCheckTrueNum(int checkTrueNum) {
        this.checkTrueNum = checkTrueNum;
    }

    public int getCheckFailNum() {
        return checkFailNum;
    }

    public void setCheckFailNum(int checkFailNum) {
        this.checkFailNum = checkFailNum;
    }

    public int getVerificationTrueNum() {
        return verificationTrueNum;
    }

    public void setVerificationTrueNum(int verificationTrueNum) {
        this.verificationTrueNum = verificationTrueNum;
    }

    public int getVerificationFailNum() {
        return verificationFailNum;
    }

    public void setVerificationFailNum(int verificationFailNum) {
        this.verificationFailNum = verificationFailNum;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(String checkCount) {
        this.checkCount = checkCount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(String remarkId) {
        this.remarkId = remarkId;
    }

    public String getCorporationType() {
        return corporationType;
    }

    public void setCorporationType(String corporationType) {
        this.corporationType = corporationType;
    }

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getVerifyTrueSum() {
        return verifyTrueSum;
    }

    public void setVerifyTrueSum(int verifyTrueSum) {
        this.verifyTrueSum = verifyTrueSum;
    }

    public String getDateDescription() {
        return dateDescription;
    }

    public void setDateDescription(String dateDescription) {
        this.dateDescription = dateDescription;
    }
}
