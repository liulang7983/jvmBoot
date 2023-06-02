package com.model.contract;



import com.model.json.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CutTableCell {
    private HexOCRResult hexOCRResult;

    public CutTableCell(HexOCRResult hexOCRResult) {
        this.hexOCRResult = hexOCRResult;
    }

    public Map<String, Object> deal() {
        Map<String, Object> data = new HashMap<>();
        List<HexSection> body = hexOCRResult.getBody();
        ArrayList<HexCell> cellTemps = new ArrayList<>();
        HexSection hexSection = body.get(0);
        List<HexCell> cells = hexSection.getCells();

        int colNum = 0;
        int rowNum = 0;
        //将跨行跨列的单元格全部保存起来
        for (HexCell cell : cells) {
            if (cell.getBr_col() > colNum) {
                colNum = cell.getBr_col();
            }
            if (cell.getBr_row() > rowNum) {
                rowNum = cell.getBr_row();
            }
        }
        List<Map<String, Object>> maps = new ArrayList<>();

        for (int i = 0; i < rowNum; i++) {
            MyRectangle rowCoord = new MyRectangle();
            Map<String, Object> cellRowMap = new HashMap<>();
            int lastX = 0;
            //获取行坐标
            for (HexCell cell : cells) {
                if (cell.getTl_row() <= i && cell.getBr_row() >= i + 1) {

                    if (cell.getTl_row() == i && rowCoord.x == 0) {
                        rowCoord.x = cell.getItemcoord().x;
                    }
                    if (cell.getTl_row() == i && cell.getBr_row() == i + 1) {
                        rowCoord.height = cell.getItemcoord().height;
                        rowCoord.y = cell.getItemcoord().y;
                    }
                    if (cell.getItemcoord().x > lastX) {
                        lastX = cell.getItemcoord().x;
                        rowCoord.width = lastX + cell.getItemcoord().width;
                    }
                }
            }


            for (int j = 0; j < colNum; j++) {
                HexCell cellTemp = new HexCell();
                for (HexCell cell : cells) {
                    if (cell.getTl_row() <= i && cell.getBr_row() > i
                            && cell.getTl_col() <= j && cell.getBr_col() > j) {
                        cellTemp = cell;
                        break;
                    }
                }
                HexCell hexCell = new HexCell();
                hexCell.setTl_row(i);
                hexCell.setBr_row(i + 1);
                hexCell.setTl_col(j);
                hexCell.setBr_col(j + 1);
                hexCell.setItemstring(cellTemp.getItemstring());
                cellRowMap.put(j + "", cellTemp.getItemstring().replaceAll("</br>", ""));
                cellTemps.add(hexCell);
            }
            cellRowMap.put("pos", rowCoord);
            maps.add(cellRowMap);
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < colNum; i++) {
            list.add(i + "");
        }
        data.put("table", maps);
        data.put("head", list);
        data.put("type", 1);
        return data;
    }

    public HexOCRResult dealText() {
        hexOCRResult.setType(0);
        List<HexSection> hexSections = hexOCRResult.getBody();
        for(HexSection hexSection : hexSections){
            ArrayList<HexRow> hexRows = hexSection.getRows();
            for(HexRow hexRow : hexRows){
                ArrayList<Item> items =  hexRow.getRow();
                for(Item item : items){
                    item.setItemcoord(null);
                }
            }
        }
        return hexOCRResult;
    }
}
