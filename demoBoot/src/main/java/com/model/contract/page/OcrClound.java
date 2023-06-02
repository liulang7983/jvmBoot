package com.model.contract.page;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by kang on 2019-5-11.
 */
public class OcrClound {

    @JSONField(name = "data")
    private CloundData data;

    public CloundData getData() {
        return data;
    }

    public void setData(CloundData data) {
        this.data = data;
    }
}
