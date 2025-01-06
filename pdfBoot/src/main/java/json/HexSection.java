package json;

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
    /*
    获取正文的行及其单字坐标
     */
    private List<RowCoord> rowCoords;

    /*
    获取高精度正文的行或者表格左上角的y坐标
     */
    private Integer y;

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

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public List<RowCoord> getRowCoords() {
        return rowCoords;
    }

    public void setRowCoords(List<RowCoord> rowCoords) {
        this.rowCoords = rowCoords;
    }
}
