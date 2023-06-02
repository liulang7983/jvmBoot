package com.model.contract.page;

import com.alibaba.fastjson.annotation.JSONField;

public class Cells {

    @JSONField(name = "tl_col")
    private int tlCol;

    @JSONField(name = "tl_row")
    private int tlRow;

    @JSONField(name = "br_col")
    private int brCol;

    @JSONField(name = "br_row")
    private int brRow;

    @JSONField(name = "contents")
    private Contents[] contents = new Contents[]{};

    @JSONField(name = "cell_coord_point")
    private CellCoordPoint[] cellCoordPoint = new CellCoordPoint[]{};

    @JSONField(name = "cell_content_text")
    private String cellContentText = "";

    @JSONField(name = "cell_conf")
    private float cellConf;

    @JSONField(name = "description")
    private String description;

    public int getTlCol() {
        return tlCol;
    }

    public void setTlCol(int tlCol) {
        this.tlCol = tlCol;
    }

    public int getTlRow() {
        return tlRow;
    }

    public void setTlRow(int tlRow) {
        this.tlRow = tlRow;
    }

    public int getBrCol() {
        return brCol;
    }

    public void setBrCol(int brCol) {
        this.brCol = brCol;
    }

    public int getBrRow() {
        return brRow;
    }

    public void setBrRow(int brRow) {
        this.brRow = brRow;
    }

    public Contents[] getContents() {
        return contents;
    }

    public void setContents(Contents[] contents) {
        this.contents = contents;
    }

    public CellCoordPoint[] getCellCoordPoint() {
        return cellCoordPoint;
    }

    public void setCellCoordPoint(CellCoordPoint[] cellCoordPoint) {
        this.cellCoordPoint = cellCoordPoint;
    }

    public String getCellContentText() {
        return cellContentText;
    }

    public void setCellContentText(String cellContentText) {
        this.cellContentText = cellContentText;
    }

    public float getCellConf() {
        return cellConf;
    }

    public void setCellConf(float cellConf) {
        this.cellConf = cellConf;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
