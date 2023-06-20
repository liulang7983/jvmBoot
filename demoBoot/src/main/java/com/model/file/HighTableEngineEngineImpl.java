package com.model.file;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.model.Func;
import com.model.contract.page.*;
import com.model.json.HexCell;
import com.model.json.HexOCRResult;
import com.model.json.HexSection;
import com.model.json.RowCoord;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kang on 2019-3-6.
 */
class HighTableEngineEngineImpl extends IntsigEngineImpl {

    public HighTableEngineEngineImpl() {
        ReadPageDelegate = (ocrFile, page, sbText, lineNum, wordIndexNum) ->
                ReadPage(ocrFile, page, sbText, lineNum, wordIndexNum);
    }


    @Override
    protected void ReadPage(String file,
                            PageContent page,
                            StringBuffer sbText,
                            LineNum lineNum,
                            WordIndexNum wordIndexNum) {

        //识别出来的旋转角度
        double angle = 0;
        //文本行或文本行中每个文本字的坐标
        int x, y, width, height;
        x = y = width = height = 0;
        //文本行中字+"_"+wordIndexNum
        String key_word;
        StringBuffer sbtxt = new StringBuffer();
        int pageNum;
        //File f = new File(file);
        //String fileFullName = f.getName();
        //String fileNameWithoutExt = fileFullName.substring(0, fileFullName.lastIndexOf("."));
        pageNum = page.getNum();

        //获取对应png图片的高与宽
        //int[] png_width_height = GetOriginalPngWidthHeight(file);
        try {

            StringBuffer sbLineText = new StringBuffer();
            LineText lineTextObj;
            StringBuilder sbJSON = Func.readTextFile(file, "UTF-8");
            if (0 == sbJSON.length()) {
                return;
            }
            HexOCRResult hexOCRResult = v3Tov2(sbJSON.toString());
            List<HexSection> hexSections = hexOCRResult.getBody().stream().sorted(Comparator.comparing(HexSection::getY)).collect(Collectors.toList());
            angle = hexOCRResult.getAngle();

            int num = 0;
            int line = 1;
            List<Parag> parags = new LinkedList<>();
            for (int i = 0; i < hexSections.size(); i++) {
                sbLineText.delete(0, sbLineText.length());
                HexSection hexSection = hexSections.get(i);
                LinkedHashMap lineWords = new LinkedHashMap();
                List<RowCoord> rowCoords = hexSection.getRowCoords();
                int[] line_pos = new int[4];
                line_pos[0] = line_pos[1] = line_pos[2] = line_pos[3] = 0;
                for (int j = 0; j < rowCoords.size(); j++) {
                    RowCoord rowCoord = rowCoords.get(j);
                    num++;
                    int num1 = lineNum.getNum();
                    lineNum.setNum(num1);
                    WordInfo wordInfo = new WordInfo();
                    String content = rowCoord.getContent();
                    if (StringUtils.isBlank(content)){
                        continue;
                    }
                    wordInfo.setWordTxt(content);
                    int[] value_pos = new int[4];
                    Rectangle coord = rowCoord.getCoord();
                    value_pos[0] = coord.x;
                    value_pos[1] = coord.y;
                    value_pos[2] = coord.width;
                    value_pos[3] = coord.height;
                    wordInfo.setRectangle(value_pos);
                    wordIndexNum.setNum(wordIndexNum.getNum() + 1);
                    sbLineText.append(content);
                    sbtxt.append(content);
                    wordInfo.setPageNum(pageNum);
                    key_word = String.format("%s_%s", content, wordIndexNum.getNum());
                    page.getWords().put(key_word, wordInfo);
                    lineWords.put(key_word, wordInfo);
                    page.getWordsPos().add(value_pos);
                }
                String strLineText = sbLineText.toString();
                if (!strLineText.isEmpty()) {
                    lineTextObj = new LineText();
                    lineTextObj.setWords(lineWords);
                    lineTextObj.setPageNum(page.getNum());
                    lineTextObj.setLineNum(lineNum.getNum());
                    lineTextObj.setContent(strLineText);
                    lineTextObj.setLinePos(line_pos);
                    //加入行文本对象
                    page.getLineTexts().add(lineTextObj);
                    InitLineMaxWordIndex(line, page);
                    /*生成段落信息*/
                    if (parags.size() != 0) {
                        Parag parag = parags.get(parags.size() - 1);
                        String paragString = parag.getParagContent();
                        paragString += sbLineText.toString();
                        parag.setParagContent(paragString);
                    } else {
                        Parag parag = new Parag();
                        parag.setParagContent(sbLineText.toString());
                        parags.add(parag);
                    }
                }
                line++;


            }
            sbText.append(sbtxt.toString());
            page.setParags(parags);
            page.setContent(sbtxt.toString());
            page.setAngle(angle);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static HexOCRResult v3Tov2(String v2) {
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
        List<Integer> indexList = new ArrayList<>();
        //表格循环,先把表格的挑出来(elemList里面的id会少行数，不可完全依赖elemList)
        for (int i = 0; i < elemList.size(); i++) {
            JSONObject tableElem = elemList.getJSONObject(i);
            int elemType = tableElem.getInteger("elem_type");
            //区分表格和文本
            if (elemType == 3) {
                highTable(tableElem, itemList, hexOCRResult, indexList);
            }
        }
        ArrayList<HexSection> body = hexOCRResult.getBody();
        for (int i = 0; i < itemList.size(); i++) {
            if (!indexList.contains(i)) {
                JSONObject content = itemList.getJSONObject(i);
                HexSection hexSection = new HexSection();
                Integer y = content.getJSONObject("vertex").getInteger("y1");
                hexSection.setY(y);
                List<RowCoord> rowCoords = JSONArray.parseArray(content.getJSONObject("word_info").getString("word_list"), RowCoord.class);
                hexSection.setRowCoords(rowCoords);
                body.add(hexSection);
            }
        }
        return hexOCRResult;

    }

    public static void highTable(JSONObject tableElem, JSONArray itemList, HexOCRResult hexOCRResult, List<Integer> indexList) {
        HexSection hexSection = new HexSection();
        hexSection.setType(1);
        JSONObject tableInerElem = tableElem.getJSONObject("elem_content");
        JSONArray elemInerList = tableInerElem.getJSONArray("elem_list");
        JSONObject table = elemInerList.getJSONObject(0);
        ArrayList<HexCell> hexCells = new ArrayList<>();
        JSONObject cellElem = table.getJSONObject("elem_content");
        JSONArray cellList = cellElem.getJSONArray("elem_list");
        Integer y = cellList.getJSONObject(0).getJSONObject("vertex").getInteger("y1");
        hexSection.setY(y);
        List<RowCoord> rowCoords = new ArrayList<>();
        //单元格循环
        for (int j = 0; j < cellList.size(); j++) {
            JSONObject cell = cellList.getJSONObject(j);
            JSONObject contentItemList = cell.getJSONObject("item_list");
            JSONArray ids = contentItemList.getJSONArray("id");
            if (ids.size() >= 1) {
                for (int idIndex = 0; idIndex < ids.size(); idIndex++) {
                    Integer id = ids.getInteger(idIndex);
                    indexList.add(id);
                    JSONObject content = itemList.getJSONObject(id);
                    List<RowCoord> rowCoord = JSONArray.parseArray(content.getJSONObject("word_info").getString("word_list"), RowCoord.class);
                    rowCoords.addAll(rowCoord);
                }
            }
        }
        hexSection.setRowCoords(rowCoords);
        hexOCRResult.getBody().add(hexSection);
    }
}
