package com.model.chatMort;

/**
 * 登记撤销信息
 */
public class RevokeReg {
    /*
    撤销日期
     */
    private String revokedate;
    /*
    撤销机关
     */
    private String revokeauthority;
    /*
    撤销内容
     */
    private String revokecontent;
    /*
    撤销原因
     */
    private String revokereason;

    public String getRevokedate() {
        return revokedate;
    }

    public void setRevokedate(String revokedate) {
        this.revokedate = revokedate;
    }

    public String getRevokeauthority() {
        return revokeauthority;
    }

    public void setRevokeauthority(String revokeauthority) {
        this.revokeauthority = revokeauthority;
    }

    public String getRevokecontent() {
        return revokecontent;
    }

    public void setRevokecontent(String revokecontent) {
        this.revokecontent = revokecontent;
    }

    public String getRevokereason() {
        return revokereason;
    }

    public void setRevokereason(String revokereason) {
        this.revokereason = revokereason;
    }

    @Override
    public String toString() {
        return "RevokeReg{" +
                "revokedate='" + revokedate + '\'' +
                ", revokeauthority='" + revokeauthority + '\'' +
                ", revokecontent='" + revokecontent + '\'' +
                ", revokereason='" + revokereason + '\'' +
                '}';
    }
}
