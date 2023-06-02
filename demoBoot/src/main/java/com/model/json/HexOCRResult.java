package com.model.json;

import java.util.ArrayList;

public class HexOCRResult {
    /*
    0--中登网pdf 1-中登网附件 2-企查查
     */
    private int type;

    private ArrayList<HexSection> Body;

    private Float angle;

    public HexOCRResult(){
        Body=new ArrayList<HexSection>();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<HexSection> getBody() {
        return Body;
    }

    public void setBody(ArrayList<HexSection> body) {
        Body = body;
    }

    public Float getAngle() {
        return angle;
    }

    public void setAngle(Float angle) {
        this.angle = angle;
    }
}
