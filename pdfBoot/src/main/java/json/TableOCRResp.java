package json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(
        name = "o"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        propOrder = {"errorcode", "errormsg", "angle", "tableRes"}
)
public class TableOCRResp {

    private int errorcode;

    private String errormsg;

    private Float angle;

    private TableRes tableRes;

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public Float getAngle() {
        return angle;
    }

    public void setAngle(Float angle) {
        this.angle = angle;
    }

    public TableRes getTableRes() {
        return tableRes;
    }

    public void setTableRes(TableRes tableRes) {
        this.tableRes = tableRes;
    }
}
