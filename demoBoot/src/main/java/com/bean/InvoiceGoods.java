package com.bean;

import lombok.Data;

/**
 * @Author jiajun.xiong
 * @Date 2021/4/6 14:46
 * @Version 1.0
 */
public class InvoiceGoods {

    /**
     * ID
     */
    private String goodsId;

    /**
     * INVOICE_ID关联增值税详情
     */
    private String invoiceId;

    /**
     * 序号(自己定义，后期可能不用)
     */
    private String serialNumber;

    /**
     * 货物或服务名称
     */
    private String goodsName;

    /**
     * 规格型号
     */
    private String model;

    /**
     * 单位
     */
    private String unit;

    /**
     * 数量
     */
    private String goodsNumber;

    /**
     * 单价
     */
    private String price;

    /**
     * 金额
     */
    private String money;

    /**
     * 税率
     */
    private String taxRate;
    /**
     * 税额
     */
    private String taxAmount;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    @Override
    public String toString() {
        return "InvoiceGoods{" +
                "goodsId='" + goodsId + '\'' +
                ", invoiceId='" + invoiceId + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", model='" + model + '\'' +
                ", unit='" + unit + '\'' +
                ", goodsNumber='" + goodsNumber + '\'' +
                ", price='" + price + '\'' +
                ", money='" + money + '\'' +
                ", taxRate='" + taxRate + '\'' +
                ", taxAmount='" + taxAmount + '\'' +
                '}';
    }
}
