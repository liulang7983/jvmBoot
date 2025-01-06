package json;

import java.util.ArrayList;


public class HexOCRResult {
    /**
     * 0--中登网pdf 1-中登网附件 2-企查查
     */
    private int type;

    /**
     * 内容
     */
    private ArrayList<HexSection> body =new ArrayList<>();

    /**
     * 角度
     */
    private Float angle;

    /**
     * pdf文件名，同一份pdf不同页，名称相同
     */
    private String pdfName;

    /**
     * 页码
     */
    private Integer pageNo;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<HexSection> getBody() {
        return body;
    }

    public void setBody(ArrayList<HexSection> body) {
        this.body = body;
    }

    public Float getAngle() {
        return angle;
    }

    public void setAngle(Float angle) {
        this.angle = angle;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
