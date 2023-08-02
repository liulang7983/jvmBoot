package com.bean;

/**
 * @author ming.li
 * @date 2023/8/1 18:12
 */
public class TelInfo {
    private Integer begin;
    private Integer end;
    private String num;
    private String type;

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TelInfo{" +
                "begin=" + begin +
                ", end=" + end +
                ", num='" + num + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
