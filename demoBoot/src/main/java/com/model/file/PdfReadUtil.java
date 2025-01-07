package com.model.file;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.model.Func;
import com.model.contract.page.*;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author ming.li
 * @Date 2025/1/7 14:24
 * @Version 1.0
 */
public class PdfReadUtil {
    public static void ReadPage(String file, PageContent page, StringBuffer sbText, LineNum lineNum, WordIndexNum wordIndexNum) {
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
                    lineCount++;
                }
                cellIndex++;
            }
            page.setContent(sbtxt.toString());
            page.setAngle(0);
            sbText.append(sbtxt);
        }catch (Exception e){
            System.out.println("");
        }
    }
    public static void InitLineMaxWordIndex(int lineNum, PageContent page) {
        List<WordInfo> listWordInfo = new ArrayList();
        page.getWords().forEach((pageNum, wordInfo) -> {
            if (wordInfo.getLineNum() == lineNum) {
                listWordInfo.add(wordInfo);
            }
        });
        if (null == listWordInfo || 0 == listWordInfo.size()) {
            return;
        }
        int maxWordIndex = listWordInfo.stream().mapToInt(WordInfo::getWordIndex).max().getAsInt();
        int minWordIndex = listWordInfo.stream().mapToInt(WordInfo::getWordIndex).min().getAsInt();
        listWordInfo.forEach((wordInfo) -> {
            wordInfo.setLineMinWordIndex(minWordIndex);
            wordInfo.setLineMaxWordIndex(maxWordIndex);
            String key = String.format("%s_%d", wordInfo.getWordTxt(), wordInfo.getWordIndex());
            page.getWords().put(key, wordInfo);
        });
        listWordInfo.clear();
    }
}
