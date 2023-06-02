package com.model.file;



import com.model.Func;
import com.model.contract.page.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kang on 2019-3-6.
 */
class TableEngineEngineImpl extends IntsigEngineImpl {

    public TableEngineEngineImpl() {
        ReadPageDelegate = (ocrFile, page, sbText, lineNum, wordIndexNum) ->
                ReadPage(ocrFile, page, sbText, lineNum, wordIndexNum);
    }


    @Override
    protected void ReadPage(String file,
                            PageContent page,
                            StringBuffer sbText,
                            LineNum lineNum,
                            WordIndexNum wordIndexNum){

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
            StringBuilder sbJSON = Func.readTextFile(file,"UTF-8");
            if (0 == sbJSON.length()){
                return;
            }

            OcrTable obj = Func.jsonString2Object(sbJSON.toString(), OcrTable.class);
            angle = obj.getAngle();

            int num = 0;
            int line = 1;
            List<Parag> parags = new LinkedList<>();
            TableRes tableRes = obj.getTableRes();
            Tables[] tables = tableRes.getTables();
            for (Tables table:tables){
                Cells[] cells = table.getCells();
                for (Cells cell:cells){
                    Contents[] contents = cell.getContents();
                    for (Contents content:contents){
                        sbLineText.delete(0, sbLineText.length());
                        int lineX = content.getItemcoord().getX();
                        int lineY = content.getItemcoord().getY();
                        int lineWidth = content.getItemcoord().getWidth();
                        int lineHeight = content.getItemcoord().getHeight();
                        int[] line_pos = new int[4];
                        line_pos[0] = lineX;
                        line_pos[1] = lineY;
                        line_pos[2] = lineWidth;
                        line_pos[3] = lineHeight;
                        num++;
                        int num1 = lineNum.getNum();
                        lineNum.setNum(num1);
                        int wordCount = content.getWords().length;
                        LinkedHashMap lineWords = new LinkedHashMap();
                        boolean flag = false;
                        for (int i=0;i<wordCount;i++){
                            if (content.getWords()[i].getCharacter().trim().isEmpty()){
                                continue;
                            }
                            //文本行中字的索引位计数
                            wordIndexNum.setNum(wordIndexNum.getNum()+1);
                            //追加文本行中的字串（追加到页对象中）
                            sbtxt.append(content.getWords()[i].getCharacter().trim());
                            //追加文本行中的字串（追加到行文本对象中）
                            sbLineText.append(content.getWords()[i].getCharacter().trim());
                            //读取文本行中的字的坐标
                            int[] pos = new int[4];
                            pos[0] = pos[1] = pos[2] = pos[3] = 0;
                            x = content.getCoords()[i].getX();
                            y = content.getCoords()[i].getY();
                            width = content.getCoords()[i].getWidth();
                            height = content.getCoords()[i].getHeight();
                            key_word = String.format("%s_%s",
                                    content.getWords()[i].getCharacter().trim(),
                                    wordIndexNum.getNum());
                            //文本行中字的坐标
                            int[] value_pos = new int[4];
                            value_pos[0] = x;
                            value_pos[1] = y;
                            value_pos[2] = width;
                            value_pos[3] = height;
                            //记录当前字的信息
                            WordInfo wordInfo = new WordInfo();
                            wordInfo.setWordTxt(content.getWords()[i].getCharacter().trim());
                            // TODO: 2022/9/7 页数
                            wordInfo.setPageNum(pageNum);
                            flag = true;
                            wordInfo.setLineNum(line);
                            wordInfo.setWordIndex(wordIndexNum.getNum());
                            wordInfo.setRectangle(value_pos);
                            wordInfo.setLineRectangle(line_pos);
                            wordInfo.setConfidence(content.getWords()[i].getConfidence());
                            if (content.getCandWord().length != 0){
                                wordInfo.setWords(content.getCandWord()[i].getWords());
                            }
                            page.getWords().put(key_word,wordInfo);
                            lineWords.put(key_word,wordInfo);
                            page.getWordsPos().add(value_pos);
                        }

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
                            InitLineMaxWordIndex(line, page);
                            /*生成段落信息*/
                            int paragNum = content.getParag().getParagNo();
                            if (parags.size() != 0){
                                Parag parag = parags.get(parags.size()-1);
                                if (parag.getParagNo() == paragNum){
                                    String paragString = parag.getParagContent();
                                    paragString += sbLineText.toString();
                                    parag.setParagContent(paragString);
                                }else {
                                    Parag parag1 = new Parag();
                                    parag1.setParagNo(paragNum);
                                    parag1.setParagContent(sbLineText.toString());
                                    parags.add(parag1);

                                }
                            }else {
                                Parag parag = new Parag();
                                parag.setParagNo(content.getParag().getParagNo());
                                parag.setParagContent(sbLineText.toString());
                                parags.add(parag);
                            }
                        }
                        if (flag){
                            line++;
                        }
                    }
                }
            }
            page.setParags(parags);
            page.setContent(sbtxt.toString());
            page.setAngle(angle);
            sbText.append(sbtxt.toString());
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
