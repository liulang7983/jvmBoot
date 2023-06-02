package com.model.contract.page;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * 文本块
 * Created by kang on 2019-1-22.
 */
public class LineText implements Serializable {

    private static final long serialVersionUID = 3699245142338084551L;

    /**
     * 要素分类编号
     */
    private int classNo = 0;

    /**
     * 文本块所在页数
     */
    private int pageNum = 0;

    /**
     * 文本块所在页的行数
     */
    private int lineNum = 0;

    /**
     * 文本块内容
     */
    private String content = "";

    private LinkedHashMap<String, WordInfo> words = new LinkedHashMap<>();

    /**
     * 文本块rectangle(x,y,width,height)
     */
    private int[] linePos = new int[4];

    public LinkedHashMap<String, WordInfo> getWords() {
        return words;
    }

    public void setWords(LinkedHashMap<String, WordInfo> words) {
        this.words = words;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int[] getLinePos() {
        return linePos;
    }

    public void setLinePos(int[] linePos) {
        this.linePos = linePos;
    }
}
