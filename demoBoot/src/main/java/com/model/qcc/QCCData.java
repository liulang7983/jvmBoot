package com.model.qcc;

import java.util.List;

public class QCCData {
    private Integer VerifyResult;
    private List<QCCRegister> Data;

    public Integer getVerifyResult() {
        return VerifyResult;
    }

    public void setVerifyResult(Integer verifyResult) {
        VerifyResult = verifyResult;
    }

    public List<QCCRegister> getData() {
        return Data;
    }

    public void setData(List<QCCRegister> data) {
        Data = data;
    }
}
