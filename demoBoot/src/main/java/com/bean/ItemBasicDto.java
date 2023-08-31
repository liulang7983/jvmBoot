package com.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WE1
 * @date 2020/8/5 15:56
 */
@Data
public class ItemBasicDto implements Serializable {
    /**
     * 识别出的字段名称对应的值，也就是字段item对应的字符串结果
     */
    private String itemstring;
    /**
     * 识别出的字段名称（关键字）
     */
    private String item;
    /**
     * 置信度
     */
    private Float itemconf;
    /**
     * 文本行在旋转纠正之后的图像中的像素坐标，表示为（左上角x, 左上角y，宽width，高height）
     */
    private ItemCoordDto itemcoord;
}
