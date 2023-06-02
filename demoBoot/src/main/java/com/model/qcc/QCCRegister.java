package com.model.qcc;

public class QCCRegister {
    private String RegisterNo;
    private String RegisterDate;
    private String Status;
    private QCCDetail Detail;
    private String DebtSecuredAmount;

    public String getRegisterNo() {
        return RegisterNo;
    }

    public void setRegisterNo(String registerNo) {
        RegisterNo = registerNo;
    }

    public String getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(String registerDate) {
        RegisterDate = registerDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public QCCDetail getDetail() {
        return Detail;
    }

    public void setDetail(QCCDetail detail) {
        Detail = detail;
    }

    public String getDebtSecuredAmount() {
        return DebtSecuredAmount;
    }

    public void setDebtSecuredAmount(String debtSecuredAmount) {
        DebtSecuredAmount = debtSecuredAmount;
    }
}
