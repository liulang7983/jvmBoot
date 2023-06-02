package com.model.qcc;

public class QCCResponse {
    private QCCPaging Paging;
    private QCCData Result;
    private String Status;
    private String Message;

    public QCCPaging getPaging() {
        return Paging;
    }

    public void setPaging(QCCPaging paging) {
        Paging = paging;
    }

    public QCCData getResult() {
        return Result;
    }

    public void setResult(QCCData result) {
        Result = result;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
