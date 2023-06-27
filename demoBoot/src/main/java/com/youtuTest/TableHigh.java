package com.youtuTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.model.json.*;
import com.util.FileUtil;

import java.awt.*;
import java.util.ArrayList;

public class TableHigh {

    public static void main(String[] args) {
        String v3Str = FileUtil.readFileToString("C:\\Users\\ken\\Desktop\\日志\\识别结果\\优图\\A提取为逗号.json", "utf-8");
        String v2Str = v3Tov2(v3Str);
        System.out.println(v2Str);
    }

    private static String v3Tov2(String v2){
        JSONObject v2Json = JSON.parseObject(v2);
        JSONArray recognizeList = v2Json.getJSONArray("recognize_list");
        JSONObject itemContent = recognizeList.getJSONObject(0).getJSONObject("item_content");
        float angle = recognizeList.getJSONObject(0).getFloat("angle");
        JSONArray itemList = itemContent.getJSONArray("item_list");
        HexOCRResult hexOCRResult = new HexOCRResult();
        hexOCRResult.setAngle(angle);
        hexOCRResult.setType(0);
        JSONObject elemContent = recognizeList.getJSONObject(0).getJSONObject("elem_content");
        JSONArray elemList = elemContent.getJSONArray("elem_list");
        //表格循环
        for (int i=0;i<elemList.size();i++){

            JSONObject tableElem = elemList.getJSONObject(i);
            int elemType = tableElem.getInteger("elem_type");
            //区分表格和文本
            if (elemType==3){
                highTable(tableElem,itemList,hexOCRResult);
            }else if (elemType==2){
                highText(tableElem,itemList,hexOCRResult);
            }
        }
        return null;

    }

    public static void highTable(JSONObject tableElem, JSONArray itemList, HexOCRResult hexOCRResult){
        HexSection hexSection = new HexSection();
        hexSection.setType(1);
        JSONObject tableInerElem = tableElem.getJSONObject("elem_content");
        JSONArray elemInerList = tableInerElem.getJSONArray("elem_list");
        JSONObject table = elemInerList.getJSONObject(0);
        ArrayList<HexCell> hexCells=new ArrayList<>();
        JSONObject cellElem = table.getJSONObject("elem_content");
        JSONArray cellList = cellElem.getJSONArray("elem_list");
        //单元格循环
        for (int j=0;j<cellList.size();j++){
            JSONObject cell = cellList.getJSONObject(j);
            //创建单元格对象
            HexCell hexCell = new HexCell();
            int tl_col = cell.getInteger("tl_col");
            int tl_row = cell.getInteger("tl_row");
            int br_col = cell.getInteger("br_col");
            int br_row = cell.getInteger("br_row");
            hexCell.setBr_col(br_col);
            hexCell.setBr_row(br_row);
            hexCell.setTl_col(tl_col);
            hexCell.setTl_row(tl_row);
            StringBuilder cellContentTextSb = new StringBuilder();
            JSONObject vertex = JSONObject.parseObject(cell.getString("vertex"));
            Integer x11 = vertex.getInteger("x1");
            Integer y11 = vertex.getInteger("y1");
            Integer x33 = vertex.getInteger("x3");
            Integer y33 = vertex.getInteger("y3");
            Rectangle coord=new Rectangle();
            coord.setBounds(x11,y11,x33-x11,y33-y11);
            hexCell.setItemcoord(coord);
            JSONObject contentItemList = cell.getJSONObject("item_list");
            JSONArray ids = contentItemList.getJSONArray("id");
            if(ids.size()>=1){
                for (int idIndex = 0;idIndex<ids.size();idIndex++){
                    int id = ids.getInteger(idIndex);
                    JSONObject content = itemList.getJSONObject(id);
                    //获取v3中content的内容
                    String contentStr = content.getString("content");
                    cellContentTextSb.append(contentStr);
                }
            }
            hexCell.setItemstring(cellContentTextSb.toString());
            hexCells.add(hexCell);
        }
        hexSection.setCells(hexCells);
        hexOCRResult.getBody().add(hexSection);
    }

    public static void highText(JSONObject tableElem, JSONArray itemList, HexOCRResult hexOCRResult){
        HexSection hexSection = new HexSection();
        hexSection.setType(0);
        JSONObject tableInerElem = tableElem.getJSONObject("elem_content");
        JSONArray elemInerList = tableInerElem.getJSONArray("elem_list");
        JSONObject table = elemInerList.getJSONObject(0);
        ArrayList<HexRow> hexRows=new ArrayList<>();
        JSONObject cellElem = table.getJSONObject("elem_content");
        JSONArray cellList = cellElem.getJSONArray("elem_list");
        //行数循环

        for (int j=0;j<cellList.size();j++){
            JSONObject cell = cellList.getJSONObject(j);
            //创建行数对象
            HexRow hexRow = new HexRow();
            Item item = new Item();
            StringBuilder cellContentTextSb = new StringBuilder();
            JSONObject contentItemList = cell.getJSONObject("item_list");
            JSONArray ids = contentItemList.getJSONArray("id");
            if(ids.size()>=1){
                for (int idIndex = 0;idIndex<ids.size();idIndex++){
                    int id = ids.getInteger(idIndex);
                    JSONObject content = itemList.getJSONObject(id);
                    //获取v3中content的内容
                    String contentStr = content.getString("content");
                    cellContentTextSb.append(contentStr);
                    Rectangle coord = JSONObject.parseObject(content.getString("coord"), Rectangle.class);
                    item.setItemcoord(coord);
                    Integer paragNo = content.getJSONObject("layout").getInteger("parag_no");
                    Parag parag = new Parag();
                    parag.setParagNo(paragNo);
                    item.setParag(parag);
                }
            }
            item.setItemstring(cellContentTextSb.toString());
            hexRow.getRow().add(item);
            hexRows.add(hexRow);
        }
        hexSection.setRows(hexRows);
        hexOCRResult.getBody().add(hexSection);
    }

}
