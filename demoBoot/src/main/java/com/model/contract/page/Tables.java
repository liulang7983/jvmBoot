package com.model.contract.page;

import com.alibaba.fastjson.annotation.JSONField;

public class Tables {

    @JSONField(name = "cells")
    private Cells[] cells = new Cells[]{};

    @JSONField(name = "type")
    private int type;

    @JSONField(name = "title")
    private Title[] title = new Title[]{};

    @JSONField(name = "table_coord_point")
    private TableCoordPoint[] tableCoordPoints = new TableCoordPoint[]{};

    public Cells[] getCells() {
        return cells;
    }

    public void setCells(Cells[] cells) {
        this.cells = cells;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Title[] getTitle() {
        return title;
    }

    public void setTitle(Title[] title) {
        this.title = title;
    }

    public TableCoordPoint[] getTableCoordPoints() {
        return tableCoordPoints;
    }

    public void setTableCoordPoints(TableCoordPoint[] tableCoordPoints) {
        this.tableCoordPoints = tableCoordPoints;
    }
}
