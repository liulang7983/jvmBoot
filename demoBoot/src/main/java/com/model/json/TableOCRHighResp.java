package com.model.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


@XmlRootElement(
        name = "o"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        propOrder = {"error_code", "error_message", "recognize_list"}
)
public class TableOCRHighResp {

    private int error_code;

    private String error_message;
    private List<RecognizeList> recognize_list;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public List<RecognizeList> getRecognize_list() {
        return recognize_list;
    }

    public void setRecognize_list(List<RecognizeList> recognize_list) {
        this.recognize_list = recognize_list;
    }
}
