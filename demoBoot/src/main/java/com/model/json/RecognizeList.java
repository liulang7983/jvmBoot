package com.model.json;

/**
 * @author ming.li
 * @date 2023/6/2 16:10
 */
public class RecognizeList {
    private Float angle;
    private ItemContent item_content;
    private ElemContent elem_content;

    public Float getAngle() {
        return angle;
    }

    public void setAngle(Float angle) {
        this.angle = angle;
    }

    public ItemContent getItem_content() {
        return item_content;
    }

    public void setItem_content(ItemContent item_content) {
        this.item_content = item_content;
    }

    public ElemContent getElem_content() {
        return elem_content;
    }

    public void setElem_content(ElemContent elem_content) {
        this.elem_content = elem_content;
    }
}
