package com.model.contract.page;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by kang on 2020-3-20.
 */
public class OcrData {

    @JSONField(name = "items")
    private OcrHexItem[] items = new OcrHexItem[]{};

    @JSONField(name = "angle")
    private double Angle;

    public double getAngle() {
        return Angle;
    }

    public void setAngle(double angle) {
        this.Angle = angle;
    }

    public OcrHexItem[] getItems() {
        return items;
    }

    public void setItems(OcrHexItem[] items) {
        this.items = items;
    }
}
