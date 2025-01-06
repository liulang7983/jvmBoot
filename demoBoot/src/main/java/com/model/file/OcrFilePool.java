package com.model.file;

import com.model.contract.OcrEngineOpt;
import com.model.contract.OcrPageSource;
import com.model.contract.page.*;
import com.model.file.delegate.IParseOcrFile;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ocr处理返回的识别文件处理类
 * Created by kang on 2019-1-25.
 */
public abstract class OcrFilePool {

    private String BatchNo = "";

    private String LogFilePath = "";

    /**
     * 需要排除的页面上的页眉页脚rectangle
     */
    private Map<Integer, List<Rectangle>> EncludeHeaderFooterRegion = new HashMap<>();

    /**
     * 需要排除的页面上的表格rectangle
     */
    private Map<Integer, List<Rectangle>> EncludeTableRegion = new HashMap<>();

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
    }

    public String getLogFilePath() {
        return LogFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        LogFilePath = logFilePath;
    }

    public Map<Integer, List<Rectangle>> getEncludeHeaderFooterRegion() {
        return EncludeHeaderFooterRegion;
    }

    public void setEncludeHeaderFooterRegion(Map<Integer, List<Rectangle>> encludeHeaderFooterRegion) {
        EncludeHeaderFooterRegion = encludeHeaderFooterRegion;
    }

    public Map<Integer, List<Rectangle>> getEncludeTableRegion() {
        return EncludeTableRegion;
    }

    public void setEncludeTableRegion(Map<Integer, List<Rectangle>> encludeTableRegion) {
        EncludeTableRegion = encludeTableRegion;
    }

    /**
     * 指定页数和rectangle是否在页眉或页脚区域中
     *
     * @param pageNum 页数
     * @param rect    区域rectangle
     * @return
     */
    protected String InExcludeHeaderFootRegion(int pageNum, Rectangle rect) {
        boolean flag = false;
        String posFlag = "";
        if (EncludeHeaderFooterRegion.keySet().contains(pageNum)) {
            List<Rectangle> list = EncludeHeaderFooterRegion.get(pageNum);
            for (int i = 0; i < list.size(); i++) {
                Rectangle region = list.get(i);
                //判断region是页眉rectangle，还是页脚rectangle
                double distance = 10;
                Point point = new Point((int) (rect.getX() * 2 + rect.getWidth()) / 2, (int) (rect.getY() * 2 + rect.getHeight()) / 2);
                if (i == 1) {
                    //region是页脚rectangle
                    distance = rect.getY() + rect.getHeight() - region.getY();
                    posFlag = "footer";
                    flag = region.intersects(rect) && 10 <= distance;
                } else if (i == 0) {
                    posFlag = "header";
                    flag = region.contains(point) && 10 <= distance;
                } else {
                    posFlag = "border";
                    flag = region.contains(point);
                }
                if (flag) {
                    return posFlag;
                }
            }
        }
        posFlag = "";
        return posFlag;
    }
    /**
     * 指定页数和rectangle是否在表格rectangle区域中
     * @param pageNum   页数
     * @param rect      区域rectangle
     * @return
     */
    protected boolean InExcludeTableRegion(int pageNum, Rectangle rect){
        boolean flag = false;
        if (!EncludeTableRegion.containsKey(pageNum)) {
            return flag;
        }
        List<Rectangle> list = EncludeTableRegion.get(pageNum);
        Point point = new Point((rect.x * 2 + rect.width) / 2, (rect.y * 2 + rect.height) / 2);
        long num = list.stream().filter(c -> c.contains(point)).count();
        flag = 0 < num;
        return flag;
    }

    private OcrEngineOpt engineOpt = OcrEngineOpt.HEX_OCR;

    private OcrPageSource fileSource = OcrPageSource.SOURCE;

    /**
     * 是否在读取ocr JSON时，排除掉表格区域文字
     */
    private boolean isEncludeTableRegion = true;

    public OcrEngineOpt getEngineOpt() {
        return engineOpt;
    }

    public void setEngineOpt(OcrEngineOpt engineOpt) {
        this.engineOpt = engineOpt;
    }

    public OcrPageSource getFileSource() {
        return fileSource;
    }

    public void setFileSource(OcrPageSource fileSource) {
        this.fileSource = fileSource;
    }

    public boolean isEncludeTableRegion() {
        return isEncludeTableRegion;
    }

    public void setEncludeTableRegion(boolean encludeTableRegion) {
        isEncludeTableRegion = encludeTableRegion;
    }


    /**
     * 读取ocr识别的文件
     * @param cText
     */
    public void ReadOcrFile(List<String> list, Compare cText) {

        StringBuffer sbText = new StringBuffer();
        WordIndexNum wordIndexNum = new WordIndexNum();
        wordIndexNum.setNum(-1);
        LineNum lineNum = new LineNum();
        int pageNum = 0;
        //list = list.stream().filter(new File()->f.isFile()).collect(Collectors.toList());
        for(String path : list){
            pageNum++;
            PageContent page = new PageContent();
            page.setNum(pageNum);


            ReadPageDelegate.ReadEachPage(path,page,sbText,lineNum,wordIndexNum);


            page.getWords().forEach((k,v)->
                    cText.getWords().put(k, v)
            );
            cText.getPageContents().add(page);
            cText.getLineTexts().addAll(page.getLineTexts());
        }
        //完整的文本
        cText.setText(sbText.toString());


    }

    protected IParseOcrFile ReadPageDelegate=null;

    protected abstract void ReadPage(String file,
                                     PageContent page,
                                     StringBuffer sbText,
                                     LineNum lineNum,
                                     WordIndexNum wordIndexNum);

    protected void InitLineMaxWordIndex(int lineNum, PageContent page) {
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

    private int imageWidth;

    private int imageHeight;

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * 获取指定图片的高宽
     *
     * @param
     * @return
     */
    protected int[] GetOriginalPngWidthHeight(Float pageWidth, Float pageHeight) {
        imageWidth = Math.round(pageWidth);
        imageHeight = Math.round(pageHeight);
        int[] wh = new int[]{imageWidth, imageHeight};
        return wh;
    }

    /**
     * 如果差异所在的文本块中包含以下字符串，那么忽略掉差异
     */
    protected List<String> IgnoreString_InTextSection;

    /**
     * 获取或设置需要排除差异的rectangle区域
     */
    private Map<Integer, List<Rectangle>> encludeDifferRegtion = new HashMap();

    public Map<Integer, List<Rectangle>> getEncludeDifferRegtion() {
        return encludeDifferRegtion;
    }

    public void setEncludeDifferRegtion(Map<Integer, List<Rectangle>> encludeDifferRegtion) {
        this.encludeDifferRegtion = encludeDifferRegtion;
    }

    /**
     * 判断输入文本是否要忽略
     *
     * @param text 输入文本
     * @return true需要忽略, false不能忽略
     */
    protected boolean Exists_IngoreString_InTextSection(String text) {
        boolean flag = false;
        for (String str : IgnoreString_InTextSection) {
            if (-1 != text.indexOf(str)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
