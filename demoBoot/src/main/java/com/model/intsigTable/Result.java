package com.model.intsigTable;

import com.alibaba.fastjson.annotation.JSONField;

public class Result {
    @JSONField(name = "angle")
    private int angle;

    @JSONField(name = "height")
    private int height;

    @JSONField(name = "width")
    private int width;

    @JSONField(name = "tables")
    private Tables[] tables = new Tables[]{};

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Tables[] getTables() {
        return tables;
    }

    public void setTables(Tables[] tables) {
        this.tables = tables;
    }
}
