package com.bean;

/**
 * @Author ming.li
 * @Date 2024/6/25 14:39
 * @Version 1.0
 */
public class LeaseDetail {
    private String name;
    private Integer isDelete;
    private Integer dupStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getDupStatus() {
        return dupStatus;
    }

    public void setDupStatus(Integer dupStatus) {
        this.dupStatus = dupStatus;
    }

    public LeaseDetail(String name, Integer isDelete, Integer dupStatus) {
        this.name = name;
        this.isDelete = isDelete;
        this.dupStatus = dupStatus;
    }
}
