package com.model.contract.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 原件或扫描件的比较对象类
 * Created by kang on 2019-1-22.
 */
public class Compare implements Serializable {

    private static final long serialVersionUID = -5518992472786862381L;

    /**
     * 原件或扫描件全部的文本内容
     */
    private String text = "";

    /**
     * 原件或扫描件全部的页对象列表
     */
    private List<PageContent> pageContents = new ArrayList<>();

    /**
     * 原件或扫描件全部的文本块对象列表
     */
    private List<LineText> lineTexts = new ArrayList<>();

    /**
     * 原件或扫描件全部字
     * 字的在页中的位置+字内容，与字对象的map
     */
    private Map<String, WordInfo> words = new LinkedHashMap<>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<PageContent> getPageContents() {
        return pageContents;
    }

    public void setPageContents(List<PageContent> pageContents) {
        this.pageContents = pageContents;
    }

    public List<LineText> getLineTexts() {
        return lineTexts;
    }

    public void setLineTexts(List<LineText> lineTexts) {
        this.lineTexts = lineTexts;
    }

    public Map<String, WordInfo> getWords() {
        return words;
    }

    public void setWords(Map<String, WordInfo> words) {
        this.words = words;
    }
}
