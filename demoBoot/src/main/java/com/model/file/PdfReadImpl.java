package com.model.file;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.model.Func;
import com.model.contract.page.*;

import java.io.File;
import java.util.LinkedHashMap;

/**
 * @Author ming.li
 * @Date 2025/1/7 9:34
 * @Version 1.0
 */
public class PdfReadImpl extends OcrFilePool{
    public PdfReadImpl() {
        ReadPageDelegate = (ocrFile, page, sbText, lineNum, wordIndexNum) ->
                ReadPage(ocrFile, page, sbText, lineNum, wordIndexNum);
    }
    @Override
    protected void ReadPage(String file, PageContent page, StringBuffer sbText, LineNum lineNum, WordIndexNum wordIndexNum) {
        StringBuilder sbtxt = new StringBuilder();
        File f = new File(file);
        int pageNum = page.getNum();
        int cellIndex = 0;
        try {
            StringBuilder sbLineText = new StringBuilder();
            StringBuilder sbJSON = Func.readTextFile(file,"UTF-8");
            if (0 == sbJSON.length()){
                return;
            }
            JSONObject jsonObject = JSONObject.parseObject(sbJSON.toString());
            JSONArray body = jsonObject.getJSONArray("body");
            int lineCount = 1;
            for (int i = 0; i < body.size(); i++) {
                JSONObject bodyJSONObject = body.getJSONObject(i);
                JSONArray cells = bodyJSONObject.getJSONArray("cells");
                for (int j = 0; j <cells.size() ; j++) {
                    LinkedHashMap lineWords = new LinkedHashMap();
                    int num1 = lineNum.getNum();
                    lineNum.setNum(++num1);
                    JSONObject object = cells.getJSONObject(j);
                    JSONObject itemcoord = object.getJSONObject("itemcoord");
                    int[] line_pos = new int[4];
                    line_pos[0] = itemcoord.getDouble("x").intValue();
                    line_pos[1] = itemcoord.getDouble("y").intValue();
                    line_pos[2] = itemcoord.getDouble("width").intValue();
                    line_pos[3] = itemcoord.getDouble("height").intValue();
                    JSONArray texts = object.getJSONArray("texts");
                    for (int k = 0; k <texts.size() ; k++) {
                        JSONObject textsJSONObject = texts.getJSONObject(k);
                        String text = textsJSONObject.getString("text");
                        String coord = textsJSONObject.getString("coord");
                        String[] split = coord.split(",");
                        sbtxt.append(text);
                        sbLineText.append(text);
                        wordIndexNum.setNum(wordIndexNum.getNum()+1);
                        WordInfo wordInfo = new WordInfo();
                        wordInfo.setCellIndex(cellIndex);
                        wordInfo.setWordTxt(text);
                        wordInfo.setPageNum(pageNum);
                        wordInfo.setLineNum(lineCount);
                        int[] value_pos = new int[4];
                        value_pos[0] = Integer.valueOf(split[0]);
                        value_pos[1] = Integer.valueOf(split[1]);
                        value_pos[2] = Integer.valueOf(split[2]);
                        value_pos[3] = Integer.valueOf(split[3]);
                        wordInfo.setRectangle(value_pos);
                        wordInfo.setLineRectangle(line_pos);
                        String keyWord = String.format("%s_%s",text, wordIndexNum.getNum());
                        wordInfo.setWordIndex(wordIndexNum.getNum());
                        page.getWords().put(keyWord,wordInfo);
                        lineWords.put(keyWord,wordInfo);
                        page.getWordsPos().add(value_pos);
                    }
                    String strLineText = sbLineText.toString();
                    if (!strLineText.isEmpty()){
                        LineText lineTextObj = new LineText();
                        lineTextObj.setWords(lineWords);
                        lineTextObj.setPageNum(page.getNum());
                        lineTextObj.setLineNum(lineNum.getNum());
                        lineTextObj.setContent(strLineText);
                        lineTextObj.setLinePos(line_pos);
                        //加入行文本对象
                        page.getLineTexts().add(lineTextObj);
                        InitLineMaxWordIndex(lineCount, page);
                        /*生成段落信息*/
                    }
                    cellIndex++;
                }
                lineCount++;
            }
            page.setContent(sbtxt.toString());
            page.setAngle(0);
            sbText.append(sbtxt);
        }catch (Exception e){
            System.out.println("");
        }
    }
}
