package com.model.contract.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 文本页
 * Created by kang on 2019-1-22.
 */
public class PageContent implements Serializable {

    private static final long serialVersionUID = -7216936964598494971L;

    /**
     * 页数
     */
    private int num = 0;

    /**
     * 页内容
     */
    private String content = "";

    private List<Parag> parags;

    /**
     * 文本块列表
     */
    private List<LineText> lineTexts = new ArrayList<>();

    /**
     * 字的在页中的位置+字内容，与字对象的map
     */
    private LinkedHashMap<String, WordInfo> words = new LinkedHashMap<>();

    /**
     * 页中所有字的位置rectangle(x,y,width,height)
     */
    private List<int[]> WordsPos = new ArrayList<>();

    /**
     * 当前页面检测出的旋转角度
     */
    private double Angle = 0;

    /**
     * 图像页面的宽度
     */
    private int Width;

    /**
     * 图像页面的高度
     */
    private int Height;

    public List<Parag> getParags() {
        return parags;
    }

    public void setParags(List<Parag> parags) {
        this.parags = parags;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public double getAngle() {
        return Angle;
    }

    public void setAngle(double angle) {
        Angle = angle;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<LineText> getLineTexts() {
        return lineTexts;
    }

    public void setLineTexts(List<LineText> lineTexts) {
        this.lineTexts = lineTexts;
    }

    public LinkedHashMap<String, WordInfo> getWords() {
        return words;
    }

    public void setWords(LinkedHashMap<String, WordInfo> words) {
        this.words = words;
    }

    public List<int[]> getWordsPos() {
        return WordsPos;
    }

    public void setWordsPos(List<int[]> wordsPos) {
        WordsPos = wordsPos;
    }
}
