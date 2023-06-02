package com.model.contract.page;

import com.alibaba.fastjson.annotation.JSONField;

public class Coords {
    @JSONField(name = "x")
    private int x;

    @JSONField(name = "y")
    private int y;

    @JSONField(name = "width")
    private int width;

    @JSONField(name = "height")
    private int height;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
