package com.model.qcc;


/**
 * @author ming.li
 * @date 2022/4/19 14:10
 */

public class SecuredClaim {
    /*
    合同类型
     */
    private String Kind;
    /*
    金额
     */
    private String Amount;
    /*
    担保的范围
     */
    private String AssuranceScope;
    /*
    备注
     */
    private String Remark;

    public String getKind() {
        return Kind;
    }

    public void setKind(String kind) {
        Kind = kind;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getAssuranceScope() {
        return AssuranceScope;
    }

    public void setAssuranceScope(String assuranceScope) {
        AssuranceScope = assuranceScope;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
