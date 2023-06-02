package com.model.file;



import com.model.Func;
import com.model.contract.Json2dataConstants;
import com.model.contract.page.*;
import com.model.intsigTable.IntsigTable;
import com.model.intsigTable.Lines;
import com.model.intsigTable.TableCells;
import com.model.intsigTable.Tables;

import java.awt.*;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 读取hex ocr文件的实现类
 * Created by kang on 2019-1-25.
 */
class IntsigEngineImpl extends OcrFilePool {

    public IntsigEngineImpl() {
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
        File f = new File(file);
        //String fileFullName = f.getName();
        //String fileNameWithoutExt = fileFullName.substring(0, fileFullName.lastIndexOf("."));
        pageNum = page.getNum();
        try {
            StringBuffer sbLineText = new StringBuffer();
            LineText lineTextObj;
            StringBuilder sbJSON = Func.readTextFile(file,"UTF-8");
            if (0 == sbJSON.length()){
                return;
            }
            IntsigTable intsigTable = Func.jsonString2Object(sbJSON.toString(),IntsigTable.class);
            angle = intsigTable.getResult().getAngle();
            com.model.intsigTable.Tables[] tables = intsigTable.getResult().getTables();
            for (Tables table:tables){
                if (table.getType().equals(Json2dataConstants.PLAIN)){
                    Lines[] lines = table.getLines();
                    int num = 0;
                    int lineCount = 1;
                    List<Parag> parags = new LinkedList<>();
                    for (Lines line:lines){
                        sbLineText.delete(0, sbLineText.length());
                        int lineX = line.getPosition()[0];
                        int lineY = line.getPosition()[1];
                        int lineWidth = line.getPosition()[2] - line.getPosition()[0];
                        int lineHeight = line.getPosition()[7] - line.getPosition()[1];
                        int[] line_pos = new int[4];
                        line_pos[0] = lineX;
                        line_pos[1] = lineY;
                        line_pos[2] = lineWidth;
                        line_pos[3] = lineHeight;

                        num++;
                        int num1 = lineNum.getNum();
                        lineNum.setNum(++num1);
                        int wordCount = line.getText().length();
                        double word_confidence;
                        LinkedHashMap lineWords = new LinkedHashMap();
                        boolean flag = false;
                        for (int i=0;i<wordCount;i++){
                            if (line.getCharCandidates()[i][0].trim().isEmpty()){
                                continue;
                            }
                            int wordX = line.getCharPositions()[i][0];
                            int wordY = line.getCharPositions()[i][1];
                            int wordWidth = line.getCharPositions()[i][2] - line.getCharPositions()[i][0];
                            int wordHeight = line.getCharPositions()[i][7] - line.getCharPositions()[i][1];
                            Rectangle rectTemp = new Rectangle(wordX,wordY,wordWidth,wordHeight);

                            //文本行中字的索引位计数
                            wordIndexNum.setNum(wordIndexNum.getNum()+1);
                            //追加文本行中的字串（追加到页对象中）
                            sbtxt.append(line.getCharCandidates()[i][0].trim());
                            //追加文本行中的字串（追加到行文本对象中）
                            sbLineText.append(line.getCharCandidates()[i][0].trim());
                            //读取文本行中的字的坐标
                            int[] pos = new int[4];
                            pos[0] = pos[1] = pos[2] = pos[3] = 0;
                            x = wordX;
                            y = wordY;
                            width = wordWidth;
                            height = wordHeight;
                            key_word = String.format("%s_%s",line.getCharCandidates()[i][0].trim(),wordIndexNum.getNum());
                            //文本行中字的坐标
                            int[] value_pos = new int[4];
                            value_pos[0] = x;
                            value_pos[1] = y;
                            value_pos[2] = width;
                            value_pos[3] = height;
                            //记录当前字的信息
                            WordInfo wordInfo = new WordInfo();
                            wordInfo.setWordTxt(line.getCharCandidates()[i][0].trim());
                            wordInfo.setPageNum(pageNum);
                            flag = true;
                            wordInfo.setLineNum(lineCount);
                            wordInfo.setWordIndex(wordIndexNum.getNum());
                            wordInfo.setRectangle(value_pos);
                            wordInfo.setLineRectangle(line_pos);
                            wordInfo.setConfidence(line.getCharScores()[i]);
                            int length = line.getCharCandidates().length;
                            Words[] words = new Words[length];
                            for (int m=0;m<length;m++){
                                int length1 = line.getCharCandidates()[m].length;
                                for (int n=0;n<length1;n++){
                                    Words words1 = new Words();
                                    words1.setCharacter(line.getCharCandidates()[m][n]);
                                    words1.setConfidence(line.getCharCandidatesScore()[m][n]);
                                    words[m] = words1;
                                }
                            }
                            page.getWords().put(key_word,wordInfo);
                            lineWords.put(key_word,wordInfo);
                            page.getWordsPos().add(value_pos);
                        }
                        //生成行文本对象
                        String strLineText = sbLineText.toString();
                        if (!strLineText.isEmpty()){
                            lineTextObj = new LineText();
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
                        if (flag){
                            lineCount++;
                        }

                    }

                }else if (table.getType().equals(Json2dataConstants.TABLE_WITH_LINE)||
                        table.getType().equals(Json2dataConstants.TABLE_WITHOUT_LINE)){
                    TableCells[] tableCells = table.getTableCells();
                    for (TableCells tableCell:tableCells){
                        Lines[] lines = tableCell.getLines();


                        int num = 0;
                        int lineCount = 1;
                        List<Parag> parags = new LinkedList<>();
                        for (Lines line:lines){
                            int lineX = line.getPosition()[0];
                            int lineY = line.getPosition()[1];
                            int lineWidth = line.getPosition()[2] - line.getPosition()[0];
                            int lineHeight = line.getPosition()[7] - line.getPosition()[1];
                            int[] line_pos = new int[4];
                            line_pos[0] = lineX;
                            line_pos[1] = lineY;
                            line_pos[2] = lineWidth;
                            line_pos[3] = lineHeight;

                            num++;
                            int num1 = lineNum.getNum();
                            lineNum.setNum(++num1);
                            int wordCount = line.getText().length();
                            double word_confidence;
                            LinkedHashMap lineWords = new LinkedHashMap();
                            boolean flag = false;
                            for (int i=0;i<wordCount;i++){
                                if (line.getCharCandidates()[i][0].trim().isEmpty()){
                                    continue;
                                }
                                int wordX = line.getCharPositions()[i][0];
                                int wordY = line.getCharPositions()[i][1];
                                int wordWidth = line.getCharPositions()[i][2] - line.getCharPositions()[i][0];
                                int wordHeight = line.getCharPositions()[i][7] - line.getCharPositions()[i][1];
                                Rectangle rectTemp = new Rectangle(wordX,wordY,wordWidth,wordHeight);

                                //文本行中字的索引位计数
                                wordIndexNum.setNum(wordIndexNum.getNum()+1);
                                //追加文本行中的字串（追加到页对象中）
                                sbtxt.append(line.getCharCandidates()[i][0].trim());
                                //追加文本行中的字串（追加到行文本对象中）
                                sbLineText.append(line.getCharCandidates()[i][0].trim());
                                //读取文本行中的字的坐标
                                int[] pos = new int[4];
                                pos[0] = pos[1] = pos[2] = pos[3] = 0;
                                x = wordX;
                                y = wordY;
                                width = wordWidth;
                                height = wordHeight;
                                key_word = String.format("%s_%s",line.getCharCandidates()[i][0].trim(),wordIndexNum.getNum());
                                //文本行中字的坐标
                                int[] value_pos = new int[4];
                                value_pos[0] = x;
                                value_pos[1] = y;
                                value_pos[2] = width;
                                value_pos[3] = height;
                                //记录当前字的信息
                                WordInfo wordInfo = new WordInfo();
                                wordInfo.setWordTxt(line.getCharCandidates()[i][0].trim());
                                wordInfo.setPageNum(pageNum);
                                flag = true;
                                wordInfo.setLineNum(lineCount);
                                wordInfo.setWordIndex(wordIndexNum.getNum());
                                wordInfo.setRectangle(value_pos);
                                wordInfo.setLineRectangle(line_pos);
                                wordInfo.setConfidence(line.getCharScores()[i]);
                                int length = line.getCharCandidates().length;
                                Words[] words = new Words[length];
                                for (int m=0;m<length;m++){
                                    int length1 = line.getCharCandidates()[m].length;
                                    for (int n=0;n<length1;n++){
                                        Words words1 = new Words();
                                        words1.setCharacter(line.getCharCandidates()[m][n]);
                                        words1.setConfidence(line.getCharCandidatesScore()[m][n]);
                                        words[m] = words1;
                                    }
                                }
                                page.getWords().put(key_word,wordInfo);
                                lineWords.put(key_word,wordInfo);
                                page.getWordsPos().add(value_pos);
                            }
                            //生成行文本对象
                            String strLineText = sbLineText.toString();
                            if (!strLineText.isEmpty()){
                                lineTextObj = new LineText();
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
                            if (flag){
                                lineCount++;
                            }

                        }
                    }
                }
            }
            page.setContent(sbtxt.toString());
            page.setAngle(angle);
            sbText.append(sbtxt.toString());
        }catch (Exception e){
            System.out.println("");
        }
    }
}
