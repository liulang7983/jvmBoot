package com.model.contract.page;


import java.io.Serializable;

/**
 * 文本字
 * Created by kang on 2019-1-22.
 */
public class WordInfo implements Serializable {

    /**
     * 页数
     */
    private int pageNum = 0;

    /**
     * 行数
     */
    private int lineNum = 0;

    /**
     * 行文本rectangle(x,y,width,height)
     */
    private int[] lineRectangle = new int[4];

    /**
     * 字在文本内容中的位置
     */
    private int wordIndex = -1;

    /**
     * 字的rectangle
     */
    private int[] rectangle = new int[4];

    /**
     * 字的内容
     */
    private String wordTxt = "";

    /**
     * 字所在文本块的最末位置
     */
    private int lineMaxWordIndex = 0;

    /**
     * 字所在文本块的最开头位置
     */
    private int lineMinWordIndex = 0;

    /**
     * 识别引擎返回的字识别的置信度
     */
    private double confidence = 0;


    /**
     * 候选字
     */
    private Words[] words = new Words[]{};

    public Words[] getWords() {
        return words;
    }

    public void setWords(Words[] words) {
        this.words = words;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public int[] getLineRectangle() {
        return lineRectangle;
    }

    public void setLineRectangle(int[] lineRectangle) {
        this.lineRectangle = lineRectangle;
    }

    public int getWordIndex() {
        return wordIndex;
    }

    public void setWordIndex(int wordIndex) {
        this.wordIndex = wordIndex;
    }

    public int[] getRectangle() {
        return rectangle;
    }

    public void setRectangle(int[] rectangle) {
        this.rectangle = rectangle;
    }

    public String getWordTxt() {
        return wordTxt;
    }

    public void setWordTxt(String wordTxt) {
        this.wordTxt = wordTxt;
    }

    public int getLineMaxWordIndex() {
        return lineMaxWordIndex;
    }

    public void setLineMaxWordIndex(int lineMaxWordIndex) {
        this.lineMaxWordIndex = lineMaxWordIndex;
    }

    public int getLineMinWordIndex() {
        return lineMinWordIndex;
    }

    public void setLineMinWordIndex(int lineMinWordIndex) {
        this.lineMinWordIndex = lineMinWordIndex;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
