package com.model.chatMort;

/**
 * 登记变更信息
 */
public class ChangeReg {

    /*
    变更日期
     */
    private String changeDate;
    /*
    变更信息
     */
    private String changeInfo;

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangeInfo() {
        return changeInfo;
    }

    public void setChangeInfo(String changeInfo) {
        this.changeInfo = changeInfo;
    }

    @Override
    public String toString() {
        return "ChangeReg{" +
                "changeDate='" + changeDate + '\'' +
                ", changeInfo='" + changeInfo + '\'' +
                '}';
    }
}
