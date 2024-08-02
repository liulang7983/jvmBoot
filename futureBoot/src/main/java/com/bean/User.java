package com.bean;

/**
 * @Author ming.li
 * @Date 2024/8/2 9:13
 * @Version 1.0
 */
public class User {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public User getUser(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setId(1);
        return user;
    }
}
