package com.model.intsigTable;

import com.alibaba.fastjson.annotation.JSONField;

public class Tables {
    @JSONField(name = "type")
    private String type;

    @JSONField(name = "table_cells")
    private TableCells[] tableCells = new TableCells[]{};

    @JSONField(name = "table_rows")
    private int tableRows;

    @JSONField(name = "lines")
    private Lines[] lines = new Lines[]{};

    @JSONField(name = "table_cols")
    private int tableCols;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TableCells[] getTableCells() {
        return tableCells;
    }

    public void setTableCells(TableCells[] tableCells) {
        this.tableCells = tableCells;
    }

    public int getTableRows() {
        return tableRows;
    }

    public void setTableRows(int tableRows) {
        this.tableRows = tableRows;
    }

    public Lines[] getLines() {
        return lines;
    }

    public void setLines(Lines[] lines) {
        this.lines = lines;
    }

    public int getTableCols() {
        return tableCols;
    }

    public void setTableCols(int tableCols) {
        this.tableCols = tableCols;
    }
}
