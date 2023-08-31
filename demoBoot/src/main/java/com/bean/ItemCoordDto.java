package com.bean;

import lombok.Data;

import java.io.Serializable;

/**坐标集合
 * @author WE1
 * @date 2020/8/5 15:54
 */
@Data
public class ItemCoordDto implements Serializable {
    private Integer y;
    private Integer x;
    private Integer height;
    private Integer width;
}
