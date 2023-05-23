package com.bean;

/**
 * @author ming.li
 * @date 2023/5/18 10:48
 */
public class User {
    private Integer id;
    private String name;
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User(Integer id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }
}
