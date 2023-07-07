package com.bean;

import java.util.List;

/**
 * 用来存放识别结果和查验结果需要对比的值
 *
 * @Author jiajun.xiong
 * @Date 2021/4/29 10:33
 * @Version 1.0
 */
public class CheckTemp {
    private String invoiceCode;//发票代码
    private String invoiceNumber;//发票号码
    private String invoiceDate;//开票日期
    private String invoiceName;//发票名称
    private String checkCode;//校验码
    private String machineCode;//机器编号
    private String buyer;//购买方
    private String buyerNumber;//纳税人识别号(买方)
    private String buyerAddressTelephone;//地址、电话(买方)
    private String buyerBankAccount;//开户行及账号(买方)
    private String passwordArea;//密码区
    private String totalAmount;//合计金额
    private String totalTax;//合计税额
    private String priceTaxChina;//价税合计(中文)
    private String priceTaxNumber;//价税合计(数字)
    private String seller;//销售方
    private String sellerNumber;//纳税人识别号(卖方)
    private String sellerAddressTelephone;//地址、电话(卖方)
    private String sellerBankAccount;//开户行及账号(卖方)
    private List<InvoiceGoods> invoiceGoodsList;//发票货物集合

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

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyerNumber() {
        return buyerNumber;
    }

    public void setBuyerNumber(String buyerNumber) {
        this.buyerNumber = buyerNumber;
    }

    public String getBuyerAddressTelephone() {
        return buyerAddressTelephone;
    }

    public void setBuyerAddressTelephone(String buyerAddressTelephone) {
        this.buyerAddressTelephone = buyerAddressTelephone;
    }

    public String getBuyerBankAccount() {
        return buyerBankAccount;
    }

    public void setBuyerBankAccount(String buyerBankAccount) {
        this.buyerBankAccount = buyerBankAccount;
    }

    public String getPasswordArea() {
        return passwordArea;
    }

    public void setPasswordArea(String passwordArea) {
        this.passwordArea = passwordArea;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    public String getPriceTaxChina() {
        return priceTaxChina;
    }

    public void setPriceTaxChina(String priceTaxChina) {
        this.priceTaxChina = priceTaxChina;
    }

    public String getPriceTaxNumber() {
        return priceTaxNumber;
    }

    public void setPriceTaxNumber(String priceTaxNumber) {
        this.priceTaxNumber = priceTaxNumber;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSellerNumber() {
        return sellerNumber;
    }

    public void setSellerNumber(String sellerNumber) {
        this.sellerNumber = sellerNumber;
    }

    public String getSellerAddressTelephone() {
        return sellerAddressTelephone;
    }

    public void setSellerAddressTelephone(String sellerAddressTelephone) {
        this.sellerAddressTelephone = sellerAddressTelephone;
    }

    public String getSellerBankAccount() {
        return sellerBankAccount;
    }

    public void setSellerBankAccount(String sellerBankAccount) {
        this.sellerBankAccount = sellerBankAccount;
    }

    public List<InvoiceGoods> getInvoiceGoodsList() {
        return invoiceGoodsList;
    }

    public void setInvoiceGoodsList(List<InvoiceGoods> invoiceGoodsList) {
        this.invoiceGoodsList = invoiceGoodsList;
    }

    @Override
    public String toString() {
        return "CheckTemp{" +
                "invoiceCode='" + invoiceCode + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", invoiceName='" + invoiceName + '\'' +
                ", checkCode='" + checkCode + '\'' +
                ", machineCode='" + machineCode + '\'' +
                ", buyer='" + buyer + '\'' +
                ", buyerNumber='" + buyerNumber + '\'' +
                ", buyerAddressTelephone='" + buyerAddressTelephone + '\'' +
                ", buyerBankAccount='" + buyerBankAccount + '\'' +
                ", passwordArea='" + passwordArea + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", totalTax='" + totalTax + '\'' +
                ", priceTaxChina='" + priceTaxChina + '\'' +
                ", priceTaxNumber='" + priceTaxNumber + '\'' +
                ", seller='" + seller + '\'' +
                ", sellerNumber='" + sellerNumber + '\'' +
                ", sellerAddressTelephone='" + sellerAddressTelephone + '\'' +
                ", sellerBankAccount='" + sellerBankAccount + '\'' +
                ", invoiceGoodsList=" + invoiceGoodsList +
                '}';
    }
}
