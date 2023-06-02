package com.model.json;

import com.alibaba.fastjson.annotation.JSONField;

public class Parag {
    @JSONField(name = "word_size")
    private int wordSize;
    @JSONField(name = "parag_no")
    private int paragNo;

    public int getWordSize() {
        return wordSize;
    }

    public void setWordSize(int wordSize) {
        this.wordSize = wordSize;
    }

    public int getParagNo() {
        return paragNo;
    }

    public void setParagNo(int paragNo) {
        this.paragNo = paragNo;
    }
}
