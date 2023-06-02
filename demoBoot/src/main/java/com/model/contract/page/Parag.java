package com.model.contract.page;

import java.io.Serializable;
import java.util.List;

public class Parag implements Serializable {
    private String paragContent;
    private List<LineText> lineTexts;
    private int word_size;
    private int parag_no;

    public String getParagContent() {
        return paragContent;
    }

    public void setParagContent(String paragContent) {
        this.paragContent = paragContent;
    }

    public List<LineText> getLineTexts() {
        return lineTexts;
    }

    public void setLineTexts(List<LineText> lineTexts) {
        this.lineTexts = lineTexts;
    }

    public int getWordSize() {
        return word_size;
    }

    public void setWordSize(int word_size) {
        this.word_size = word_size;
    }

    public int getParagNo() {
        return parag_no;
    }

    public void setParagNo(int parag_no) {
        this.parag_no = parag_no;
    }
}
