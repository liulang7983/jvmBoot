package com.model.contract.page;

import com.alibaba.fastjson.annotation.JSONField;

public class OcrTable {

    @JSONField(name = "errorcode")
    private int errorcode = 0;

    @JSONField(name = "errormsg")
    private String errormsg = "";

    @JSONField(name = "angle")
    private float angle;

    @JSONField(name = "tableRes")
    private TableRes tableRes = new TableRes();

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

    public Float getAngle() {
        return angle;
    }

    public void setAngle(Float angle) {
        this.angle = angle;
    }

    public TableRes getTableRes() {
        return tableRes;
    }

    public void setTableRes(TableRes tableRes) {
        this.tableRes = tableRes;
    }
}
