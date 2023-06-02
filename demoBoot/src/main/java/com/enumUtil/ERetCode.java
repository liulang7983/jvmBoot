package com.enumUtil;

/**
 * @author ming.li
 * @date 2023/6/2 15:00
 */
public enum ERetCode {
    SUCCESS("0000"),
    FAIL("0001"),
    NO_AUTH("403"),
    NO_SESSION("9999");

    private String retCode;

    private ERetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getCode() {
        return this.retCode;
    }
}
