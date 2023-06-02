package com.bean;

import com.enumUtil.ERetCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ming.li
 * @date 2023/6/2 14:59
 */
@ApiModel("返回对象")
public class ApiResult {
    @ApiModelProperty("处理状态")
    private String status;
    @ApiModelProperty("反馈消息")
    private String message;
    @ApiModelProperty("反馈结果集")
    private Object data;
    @ApiModelProperty("消息类型")
    private String msgType;

    public ApiResult() {
    }

    public ApiResult(Object data) {
        this.data = data;
        this.status = ERetCode.SUCCESS.getCode();
        this.message = "ok";
    }

    public ApiResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isOk() {
        return ERetCode.SUCCESS.getCode().equals(this.status);
    }

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return "ApiResult{status='" + this.status + '\'' + ", message='" + this.message + '\'' + ", data=" + this.data + ", msgType='" + this.msgType + '\'' + '}';
    }
}

