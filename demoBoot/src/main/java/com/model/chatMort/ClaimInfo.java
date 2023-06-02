package com.model.chatMort;

/**
 * 债权概况
 */
public class ClaimInfo {
    /*
    数额
     */
    private String priclasecam;
    /*
    履行期限
     */
    private String timeLimit;
    /*
    备注
     */
    private String remark;

    public String getPriclasecam() {
        return priclasecam;
    }

    public void setPriclasecam(String priclasecam) {
        this.priclasecam = priclasecam;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ClaimInfo{" +
                "priclasecam='" + priclasecam + '\'' +
                ", timeLimit='" + timeLimit + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
