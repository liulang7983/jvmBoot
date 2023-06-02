package com.model.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Table {
    /*
    0为非表格文本，1为有线表格，2为无线表格
     */
    @XmlElement(
            name = "type"
    )
    private int type;

    @XmlElementWrapper(
            name = "cells"
    )
    @XmlElement(
            name = "e"
    )
    private ArrayList<Cell> cells;

    @XmlElementWrapper(
            name = "title"
    )
    @XmlElement(
            name = "e"
    )
    private List<Item> title;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public List<Item> getTitle() {
        return title;
    }

    public void setTitle(List<Item> title) {
        this.title = title;
    }
}
