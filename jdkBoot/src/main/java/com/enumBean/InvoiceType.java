package com.enumBean;

/**
 * @author ming.li
 * @date 2023/5/16 15:25
 */
public enum InvoiceType {
    SYSTEM_ERROR("010101", "系统错误"),
    SERVICE_ERROR("010102", "服务内部错误"),
    ;

    /**
     * 错误编码
     */
    String code;
    /**
     * 错误信息
     */
    String message;

    InvoiceType(String code, String message){
        this.code=code;
        this.message=message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static InvoiceType  codeOf(String code) {
        InvoiceType[] invoiceTypes = values();
        int var2 = invoiceTypes.length;
        for (int i = 0; i < var2; ++i) {
            InvoiceType enums = invoiceTypes[i];
            if (enums.code().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        return null;
    }

    public String code() {
        return this.code;
    }
}
