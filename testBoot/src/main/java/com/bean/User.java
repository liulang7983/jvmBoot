package com.bean;

/**
 * @author ming.li
 * @date 2023/9/21 16:17
 */
public class User {
    private String name;
    private Integer count;
    public User() {

    }
    public User(String name, Integer count) {
        this.name = name;
        this.count = count;
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
}
