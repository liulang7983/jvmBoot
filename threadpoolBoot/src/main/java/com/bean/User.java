package com.bean;

/**
 * @author ming.li
 * @date 2023/7/12 9:59
 */
public class User {
    private String name;
    private Integer type;

    public User(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
