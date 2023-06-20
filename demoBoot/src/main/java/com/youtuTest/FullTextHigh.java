package com.youtuTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.model.contract.OcrEngineOpt;
import com.model.contract.page.Compare;
import com.model.file.OcrFileFactory;
import com.model.file.OcrFilePool;
import com.model.json.HexCell;
import com.model.json.HexOCRResult;
import com.model.json.HexSection;
import com.model.json.RowCoord;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ming.li
 * @date 2023/6/14 16:01
 */
public class FullTextHigh {

    public static void main(String[] args) {
        OcrFileFactory ocrFileFactory = OcrFileFactory.GetInstance();
        OcrFilePool filePool = ocrFileFactory.getOcrFilePoolInstance(OcrEngineOpt.HIGHTABLE);
        Compare compare = new Compare();
        String path = "C:\\Users\\ken\\Desktop\\日志\\识别结果\\高精度2.json";
        File dir = new File(path);
        List<String> list=new ArrayList<>();
        list.add(path);
        filePool.ReadOcrFile(list,compare);
        System.out.println("sfsfs");
       /* String v3Str = FileUtil.readFileToString("C:\\Users\\ken\\Desktop\\日志\\识别结果\\高精度2.json", "utf-8");
        String v2Str = v3Tov2(v3Str);
        System.out.println(v2Str);*/
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
        List<Integer> indexList=new ArrayList<>();
        //表格循环,先把表格的挑出来(elemList里面的id会少行数，不可完全依赖elemList)
        for (int i=0;i<elemList.size();i++){

            JSONObject tableElem = elemList.getJSONObject(i);
            int elemType = tableElem.getInteger("elem_type");
            //区分表格和文本
            if (elemType==3){
                highTable(tableElem,itemList,hexOCRResult,indexList);
            }
        }
        ArrayList<HexSection> body = hexOCRResult.getBody();
        for (int i = 0; i < itemList.size(); i++) {
            if (!indexList.contains(i)){
                JSONObject content = itemList.getJSONObject(i);
                HexSection hexSection = new HexSection();
                Integer y = content.getJSONObject("vertex").getInteger("y1");
                hexSection.setY(y);
                List<RowCoord> rowCoords = JSONArray.parseArray(content.getJSONObject("word_info").getString("word_list"), RowCoord.class);
                hexSection.setRowCoords(rowCoords);
                body.add(hexSection);
            }
        }
        List<HexSection> collect = body.stream().sorted(Comparator.comparing(HexSection::getY)).collect(Collectors.toList());
        return null;

    }

    public static void highTable(JSONObject tableElem, JSONArray itemList, HexOCRResult hexOCRResult,List<Integer> indexList){
        HexSection hexSection = new HexSection();
        hexSection.setType(1);
        JSONObject tableInerElem = tableElem.getJSONObject("elem_content");
        JSONArray elemInerList = tableInerElem.getJSONArray("elem_list");
        JSONObject table = elemInerList.getJSONObject(0);
        ArrayList<HexCell> hexCells=new ArrayList<>();
        JSONObject cellElem = table.getJSONObject("elem_content");
        JSONArray cellList = cellElem.getJSONArray("elem_list");
        Integer y = cellList.getJSONObject(0).getJSONObject("vertex").getInteger("y1");
        hexSection.setY(y);
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
            JSONObject vertex = cell.getJSONObject("vertex");
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
                    Integer id = ids.getInteger(idIndex);
                    indexList.add(id);
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
}
