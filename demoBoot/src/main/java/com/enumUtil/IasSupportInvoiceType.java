package com.enumUtil;


import com.util.StringUtil;

/**
 * @Author jiajun.xiong
 * @Date 2021/1/6 15:35
 * @Version 1.0
 */
public enum IasSupportInvoiceType {
    /**
     * 出租车票
     */
    TYPE_0("0","出租车票"),
    /**
     * 定额发票
     */
    TYPE_1("1","定额发票"),
    /**
     * 火车票
     */
    TYPE_2("2","火车票"),
    /**
     * 增值税发票
     */
    TYPE_3("3","增值税发票"),
    /**
     * 客运限额发票
     */
    TYPE_4("4","客运限额发票"),
    /**
     * 机票行程单
     */
    TYPE_5("5","机票行程单"),
    /**
     * 酒店账单
     */
    TYPE_6("6","酒店账单"),
    /**
     * 购置税发票
     */
    TYPE_7("7","购置税发票"),
    /**
     * 通用机打发票
     */
    TYPE_8("8","通用机打发票"),
    /**
     * 客运发票
     */
    TYPE_9("9","客运发票"),
    /**
     * 轮船票
     */
    TYPE_10("10","轮船票"),
    /**
     * 增值税发票（卷票)
     */
    TYPE_11("11","增值税发票（卷票)"),
    /**
     * 机动车一手销售统一发票
     */
    TYPE_12("12","机动车一手销售统一发票"),
    /**
     * 过路费发票
     */
    TYPE_13("13","过路费发票"),
    /**
     * 购物小票
     */
    TYPE_14("14","购物小票"),
    /**
     *  全真发票
     */
    TYPE_16("16","全点发票"),
    /**
     *  二手车发票
     */
    TYPE_20("20","二手车发票");
    /**
     * 发票类型
     */
    private String invoiceType;

    private String typeDescription;

    IasSupportInvoiceType(String invoiceType, String typeDescription) {
        this.invoiceType = invoiceType;
        this.typeDescription = typeDescription;
    }

    public String getInvoiceType() {
        return this.invoiceType;
    }
    public String getTypeDescription(){
        return this.typeDescription;
    }

    public static String  codeOf(String code) {
        IasSupportInvoiceType[] invoiceTypes = values();
        int var2 = invoiceTypes.length;
        for (int i = 0; i < var2; ++i) {
            IasSupportInvoiceType enums = invoiceTypes[i];
            if (StringUtil.isNumeric2(code)){
                if (Integer.valueOf(enums.getInvoiceType()).equals(Integer.valueOf(code))) {
                    return enums.getTypeDescription();
                }
            }
        }
        return code;
    }
}
