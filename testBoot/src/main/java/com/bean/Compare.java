package com.bean;

import java.io.Serializable;

/**
 * @Author ming.li
 * @Date 2024/4/25 10:15
 * @Version 1.0
 */
public class Compare implements Serializable {
    private String id;
    private Integer age;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
