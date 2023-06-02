package com.model.chatMort;

import java.util.List;

/**
 * 动产抵押详细信息
 */
public class ChatMortDetResult {

    /*
    填表人
     */
    private ChatMortResult chatMortResult;
    /*
    抵押人
     */
    private ChatMortPer mortgagor;
    /*
    抵押权人集合
     */
    private List<ChatMortPer> mortgageeList;
    /*
    债权状况
     */
    private ClaimInfo claimInfo;
    /*
    抵押物集合
     */
    private List<Collateral> collateralList;
    /*
    抵押物备注
     */
    private String collateralremark;
    /*
    登记变更集合
     */
    private List<ChangeReg> changeRegList;
    /*
    登记注销
     */
    private CancelReg cancelReg;
    /*
    登记撤销集合
     */
    private List<RevokeReg> revokeRegList;

    public ChatMortResult getChatMortResult() {
        return chatMortResult;
    }

    public void setChatMortResult(ChatMortResult chatMortResult) {
        this.chatMortResult = chatMortResult;
    }

    public ChatMortPer getMortgagor() {
        return mortgagor;
    }

    public void setMortgagor(ChatMortPer mortgagor) {
        this.mortgagor = mortgagor;
    }

    public List<ChatMortPer> getMortgageeList() {
        return mortgageeList;
    }

    public void setMortgageeList(List<ChatMortPer> mortgageeList) {
        this.mortgageeList = mortgageeList;
    }

    public ClaimInfo getClaimInfo() {
        return claimInfo;
    }

    public void setClaimInfo(ClaimInfo claimInfo) {
        this.claimInfo = claimInfo;
    }

    public List<Collateral> getCollateralList() {
        return collateralList;
    }

    public void setCollateralList(List<Collateral> collateralList) {
        this.collateralList = collateralList;
    }

    public String getCollateralremark() {
        return collateralremark;
    }

    public void setCollateralremark(String collateralremark) {
        this.collateralremark = collateralremark;
    }

    public List<ChangeReg> getChangeRegList() {
        return changeRegList;
    }

    public void setChangeRegList(List<ChangeReg> changeRegList) {
        this.changeRegList = changeRegList;
    }

    public CancelReg getCancelReg() {
        return cancelReg;
    }

    public void setCancelReg(CancelReg cancelReg) {
        this.cancelReg = cancelReg;
    }

    public List<RevokeReg> getRevokeRegList() {
        return revokeRegList;
    }

    public void setRevokeRegList(List<RevokeReg> revokeRegList) {
        this.revokeRegList = revokeRegList;
    }

    @Override
    public String toString() {
        return "ChatMortDetResult{" +
                "chatMortResult=" + chatMortResult +
                ", mortgagor=" + mortgagor +
                ", mortgageeList=" + mortgageeList +
                ", claimInfo=" + claimInfo +
                ", collateralList=" + collateralList +
                ", collateralremark='" + collateralremark + '\'' +
                ", changeRegList=" + changeRegList +
                ", cancelReg=" + cancelReg +
                ", revokeRegList=" + revokeRegList +
                '}';
    }
}
