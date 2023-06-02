package com.model.intsigTable;

import com.alibaba.fastjson.annotation.JSONField;

public class IntsigTable {
    @JSONField(name = "code")
    private int code;

    @JSONField(name = "message")
    private String message;

    @JSONField(name = "duration")
    private int duration;

    @JSONField(name = "result")
    private Result result = new Result();

    @JSONField(name = "version")
    private String version;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
