package json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.awt.*;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public class Cell {
    /*
    单元格左上角的列索引
     */
    @XmlElement(
            name = "tl_col"
    )
    private int tl_col;
    /*
    单元格左上角的行索引
     */
    @XmlElement(
            name = "tl_row"
    )
    private int tl_row;
    /*
    单元格右下角的列索引
     */
    @XmlElement(
            name = "br_col"
    )
    private int br_col;

    /*
    单元格右下角的行索引
     */
    @XmlElement(
            name = "br_row"
    )
    private int br_row;

    @XmlElement(
            name = "cell_content_text"
    )
    private String cell_content_text;

    @XmlElementWrapper(
            name = "cell_coord_point"
    )
    @XmlElement(
            name = "e"
    )
    private ArrayList<Point> cell_coord_point;

    public String getCell_content_text() {
        return cell_content_text;
    }

    public void setCell_content_text(String cell_content_text) {
        this.cell_content_text = cell_content_text;
    }

    public ArrayList<Point> getCell_coord_point() {
        return cell_coord_point;
    }

    public void setCell_coord_point(ArrayList<Point> cell_coord_point) {
        this.cell_coord_point = cell_coord_point;
    }
    /*
    单元格类型，包括body、header、footer、context四种
     */
    @XmlElement(
            name = "description"
    )
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTl_col() {
        return tl_col;
    }

    public void setTl_col(int tl_col) {
        this.tl_col = tl_col;
    }

    public int getTl_row() {
        return tl_row;
    }

    public void setTl_row(int tl_row) {
        this.tl_row = tl_row;
    }

    public int getBr_col() {
        return br_col;
    }

    public void setBr_col(int br_col) {
        this.br_col = br_col;
    }

    public int getBr_row() {
        return br_row;
    }

    public void setBr_row(int br_row) {
        this.br_row = br_row;
    }

    @XmlElementWrapper(
            name = "contents"
    )
    @XmlElement(
            name = "e"
    )
    private ArrayList<Item> contents;
    public ArrayList<Item> getContents() {
        return contents;
    }

    public void setContents(ArrayList<Item> contents) {
        this.contents = contents;
    }
}
