package com.fail.demo4;

/**
 * @Author ming.li
 * @Date 2025/4/2 11:08
 * @Version 1.0
 */
public class TestBean {
    private Integer start;
    private Integer end;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public TestBean(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }
}
