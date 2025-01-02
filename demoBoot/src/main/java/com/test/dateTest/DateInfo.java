package com.test.dateTest;

import java.util.Date;

/**
 * @Author ming.li
 * @Date 2024/12/24 15:40
 * @Version 1.0
 */
public class DateInfo {
    private Integer index;
    private Date date;

    public DateInfo(Integer index, Date date) {
        this.index = index;
        this.date = date;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
