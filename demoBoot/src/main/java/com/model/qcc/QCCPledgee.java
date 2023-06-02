package com.model.qcc;

/**
 * 企查查登记企业信息
 */
public class QCCPledgee {
    private String Name;
    private String IdentityType;
    private String IdentityNo;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIdentityType() {
        return IdentityType;
    }

    public void setIdentityType(String identityType) {
        IdentityType = identityType;
    }

    public String getIdentityNo() {
        return IdentityNo;
    }

    public void setIdentityNo(String identityNo) {
        IdentityNo = identityNo;
    }
}
