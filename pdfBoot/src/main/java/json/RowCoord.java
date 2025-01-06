package json;

import java.awt.*;

/**
 * @author ming.li
 * @date 2023/6/15 13:55
 */
public class RowCoord {
    private String content;
    private Rectangle coord;
    private int cellIndex;

    public int getCellIndex() {
        return cellIndex;
    }

    public void setCellIndex(int cellIndex) {
        this.cellIndex = cellIndex;
    }

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
