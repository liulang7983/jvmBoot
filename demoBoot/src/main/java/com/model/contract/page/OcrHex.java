package com.model.contract.page;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by kang on 2019-1-25.
 */
public class OcrHex {

    @JSONField(name = "errorcode")
    private int errorcode = 0;

    @JSONField(name = "errormsg")
    private String errormsg = "";

    @JSONField(name = "data")
    private OcrData Data;

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public OcrData getData() {
        return Data;
    }

    public void setData(OcrData data) {
        Data = data;
    }
}
