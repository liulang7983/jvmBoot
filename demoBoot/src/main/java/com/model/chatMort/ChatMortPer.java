package com.model.chatMort;

/**
 * 动产抵押人员
 */
public class ChatMortPer {

    /*
    名称
     */
    private String name;
    /*
    类型
     */
    private String type;
    /*
    证件类型
     */
    private String docType;
    /*
    证件号码
     */
    private String docId;
    /*
    住所
     */
    private String address;
    /*
    0 抵押人 1 抵押权人
     */
    private Integer personType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPersonType() {
        return personType;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    public String getPersonTypeStr() {
        if (personType == null) {
            return null;
        }
        if (personType==1){
            return "抵押权人";
        }
        if (personType==0){
            return "抵押人";
        }
        return null;
    }

    @Override
    public String toString() {
        return "ChatMortPer{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", docType='" + docType + '\'' +
                ", docId='" + docId + '\'' +
                ", address='" + address + '\'' +
                ", personType=" + personType +
                '}';
    }
}
