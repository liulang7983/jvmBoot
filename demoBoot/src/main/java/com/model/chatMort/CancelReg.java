package com.model.chatMort;

/**
 * 登记注销
 */
public class CancelReg {
    /*
    注销时间
     */
    private String cancelDate;
    /*
    注销机关
     */
    private String regOrg;
    /*
    注销原因
     */
    private String cancelInfo;

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getRegOrg() {
        return regOrg;
    }

    public void setRegOrg(String regOrg) {
        this.regOrg = regOrg;
    }

    public String getCancelInfo() {
        return cancelInfo;
    }

    public void setCancelInfo(String cancelInfo) {
        this.cancelInfo = cancelInfo;
    }

    @Override
    public String toString() {
        return "CancelReg{" +
                "cancelDate='" + cancelDate + '\'' +
                ", regOrg='" + regOrg + '\'' +
                ", cancelInfo='" + cancelInfo + '\'' +
                '}';
    }
}
