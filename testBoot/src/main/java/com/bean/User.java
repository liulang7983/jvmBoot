package com.bean;

import java.util.Date;

/**
 * @author ming.li
 * @date 2023/9/21 16:17
 */
public class User {
    private String name;
    private Integer count;
    private Date date;
    private Integer index;
    public User() {

    }
    public User(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public User(String name, Integer count, Date date) {
        this.name = name;
        this.count = count;
        this.date = date;
    }

    public User(String name, Integer count, Integer index) {
        this.name = name;
        this.count = count;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
