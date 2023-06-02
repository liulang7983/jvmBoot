package com.model.intsigTable;

import com.alibaba.fastjson.annotation.JSONField;

public class TableCells {
    @JSONField(name = "end_row")
    private int endRow;

    @JSONField(name = "end_col")
    private int endCol;

    @JSONField(name = "start_row")
    private int startRow;

    @JSONField(name = "start_col")
    private int startCol;

    @JSONField(name = "text")
    private String text;

    @JSONField(name = "position")
    private int[] position = new int[]{};

    @JSONField(name = "lines")
    private Lines[] lines = new Lines[]{};

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getEndCol() {
        return endCol;
    }

    public void setEndCol(int endCol) {
        this.endCol = endCol;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public Lines[] getLines() {
        return lines;
    }

    public void setLines(Lines[] lines) {
        this.lines = lines;
    }
}
