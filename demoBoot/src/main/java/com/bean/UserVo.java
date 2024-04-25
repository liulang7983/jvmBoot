package com.bean;

/**
 * @Author ming.li
 * @Date 2024/4/10 10:01
 * @Version 1.0
 */
public class UserVo extends User {
    private String ss;

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "ss='" + ss + '\'' +
                '}';
    }
}
