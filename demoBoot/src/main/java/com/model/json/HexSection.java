package com.model.json;

import java.util.ArrayList;
import java.util.List;

public class HexSection {
    /*
    0为文本，1为表格
     */
    private int type;
    /*
    获取表格的单元格集合
     */
    private ArrayList<HexCell> cells;

    private List<Item> title = new ArrayList<>();
    /*
    获取正文的行集合
     */
    private ArrayList<HexRow> rows;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<HexCell> getCells() {
        if(cells==null){
            cells=new ArrayList<HexCell>();
        }
        return cells;
    }

    public void setCells(ArrayList<HexCell> cells) {
        this.cells = cells;
    }

    public List<Item> getTitle() {
        return title;
    }

    public void setTitle(List<Item> title) {
        this.title = title;
    }

    public ArrayList<HexRow> getRows() {
        if(rows==null){
            rows=new ArrayList<HexRow>();
        }
        return rows;
    }

    public void setRows(ArrayList<HexRow> rows) {
        this.rows = rows;
    }
}
