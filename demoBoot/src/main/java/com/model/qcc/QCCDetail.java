package com.model.qcc;

import java.util.ArrayList;

public class QCCDetail {
    private ArrayList<QCCGuarantee> GuaranteeList;

    private ArrayList<QCCPledgee> PledgeeList;

    private  SecuredClaim securedClaim;

    public ArrayList<QCCGuarantee> getGuaranteeList() {
        return GuaranteeList;
    }

    public void setGuaranteeList(ArrayList<QCCGuarantee> guaranteeList) {
        GuaranteeList = guaranteeList;
    }

    public ArrayList<QCCPledgee> getPledgeeList() {
        return PledgeeList;
    }

    public void setPledgeeList(ArrayList<QCCPledgee> pledgeeList) {
        PledgeeList = pledgeeList;
    }

    public SecuredClaim getSecuredClaim() {
        return securedClaim;
    }

    public void setSecuredClaim(SecuredClaim securedClaim) {
        this.securedClaim = securedClaim;
    }
}
