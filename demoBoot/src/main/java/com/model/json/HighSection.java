package com.model.json;

import java.util.List;

/**
 * @author ming.li
 * @date 2023/6/15 19:25
 */
public class HighSection {
    /*
   获取正文的行及其单字坐标
    */
    private List<RowCoord> rowCoords;

    /*
    获取高精度正文的行或者表格左上角的y坐标
     */
    private Integer y;
}
