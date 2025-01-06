package json;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


public class HexCell implements Serializable {

    private static long serialVersionUID = 1L;
    /*
    单元格左上角的列索引
     */
    private int tl_col;
    /*
    单元格左上角的行索引
     */
    private int tl_row;
    /*
    单元格右下角的列索引
     */
    private int br_col;
    /*
    单元格右下角的行索引
     */
    private int br_row;

    private String itemstring;
    private Rectangle itemcoord;
    private String hightlight;
    private boolean isSplitCell;

    //单字坐标  x,y,width,height
    private List<String> coords;

    //指定内容的单元格坐标
    private RegionPosition regionPosition;

    //内部组合单元格
    private List<HexCell> combinedCells;

    public HexCell() {
        this.itemcoord = new Rectangle();
        this.isSplitCell = false;
    }

    public String getColumnPosition(RuleTypeEnum ruleType){
        if (Objects.isNull(regionPosition)){
            return null;
        }
        switch (ruleType){
            case UUID_OUT:
                return regionPosition.getLeaseUuidCoord();
            case INVOICE_NO:
                return regionPosition.getOwnerNoCoord();
            case LEASE_NAME:
                return regionPosition.getLeaseNameCoord();
            case LEASE_SPEC:
                return regionPosition.getLeaseSpecCoord();
            case SUPPLIER:
                return regionPosition.getSupplierCoord();
            case ADDRESS:
                return regionPosition.getAddressCoord();
            default:
                return null;
        }
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        HexCell.serialVersionUID = serialVersionUID;
    }

    public int getTl_col() {
        return tl_col;
    }

    public void setTl_col(int tl_col) {
        this.tl_col = tl_col;
    }

    public int getTl_row() {
        return tl_row;
    }

    public void setTl_row(int tl_row) {
        this.tl_row = tl_row;
    }

    public int getBr_col() {
        return br_col;
    }

    public void setBr_col(int br_col) {
        this.br_col = br_col;
    }

    public int getBr_row() {
        return br_row;
    }

    public void setBr_row(int br_row) {
        this.br_row = br_row;
    }

    public String getItemstring() {
        return itemstring;
    }

    public void setItemstring(String itemstring) {
        this.itemstring = itemstring;
    }

    public Rectangle getItemcoord() {
        return itemcoord;
    }

    public void setItemcoord(Rectangle itemcoord) {
        this.itemcoord = itemcoord;
    }

    public String getHightlight() {
        return hightlight;
    }

    public void setHightlight(String hightlight) {
        this.hightlight = hightlight;
    }

    public boolean isSplitCell() {
        return isSplitCell;
    }

    public void setSplitCell(boolean splitCell) {
        isSplitCell = splitCell;
    }

    public List<String> getCoords() {
        return coords;
    }

    public void setCoords(List<String> coords) {
        this.coords = coords;
    }

    public RegionPosition getRegionPosition() {
        return regionPosition;
    }

    public void setRegionPosition(RegionPosition regionPosition) {
        this.regionPosition = regionPosition;
    }

    public List<HexCell> getCombinedCells() {
        return combinedCells;
    }

    public void setCombinedCells(List<HexCell> combinedCells) {
        this.combinedCells = combinedCells;
    }
}
