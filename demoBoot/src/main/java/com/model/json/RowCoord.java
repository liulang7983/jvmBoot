package com.model.json;

import java.awt.*;
import java.util.List;

/**
 * @author ming.li
 * @date 2023/6/15 13:55
 */
public class RowCoord {
    private String content;
    private Rectangle coord;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Rectangle getCoord() {
        return coord;
    }

    public void setCoord(Rectangle coord) {
        this.coord = coord;
    }
}
