package com.bean;

import lombok.Data;

/**
 * @author ming.li
 * @date 2023/5/18 10:48
 */
public class User2 {
    private Long id;
    private String name;
    private String message;
    private Boolean ret_image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (message==null){
            this.message="s";
        }
    }

    public Boolean getRet_image() {
        return ret_image;
    }

    public void setRet_image(Boolean ret_image) {
        this.ret_image = ret_image;
    }
}
