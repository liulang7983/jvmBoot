package com.yg.edu.completableFutureTest;

/**
 * @author ming.li
 * @date 2023/11/27 19:45
 */
public class UserInfo {
    private String id;
    private String name;
    private Integer age;

    public UserInfo(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
