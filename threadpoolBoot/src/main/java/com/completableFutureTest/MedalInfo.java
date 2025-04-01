package com.completableFutureTest;

/**
 * @author ming.li
 * @date 2023/11/27 19:46
 */
public class MedalInfo {
    private String id;
    private String name;

    public MedalInfo(String id, String name) {
        this.id = id;
        this.name = name;
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
}
