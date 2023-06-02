package com.model.contract.page;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by kang on 2019-5-11.
 */
public class CloundData {

    @JSONField(name = "items")
    private OcrHexItem[] items;

    public OcrHexItem[] getItems() {
        return items;
    }

    public void setItems(OcrHexItem[] items) {
        this.items = items;
    }

    @JSONField(name = "angle")
    private double Angle;

    public double getAngle() {
        return Angle;
    }

    @JSONField(name = "angle")
    public void setAngle(double angle) {
        this.Angle = angle;
    }
}
