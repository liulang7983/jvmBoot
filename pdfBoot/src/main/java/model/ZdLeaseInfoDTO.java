package model;

/**
 * nlp租赁物信息
 */


public class ZdLeaseInfoDTO {

    /*
     主键ID
     */
    private Long nlpZdlId;
    /*
     关联RegFile
     */
    private Long nlpRfId;
    /*
     租赁物名称
     */
    private String leaseName;
    /*
     租赁物型号
     */
    private String leaseSpec;
    /*
     租赁物数量
     */
    private Float leaseNum;
    /*
     权属编号
     */
    private String ownerNo;
    /*
     唯一识别码
     */
    private String leaseUuid;
    /*
     卖方
     */
    private String lessor;
    /*
     登记证明编号
     */
    private String registerNo;

    //业务类型
    private String bussType;

    //
    private String hexCell;

    //供应商
    private String supplier;
    //地址
    private String address;

    public Long getNlpZdlId() {
        return nlpZdlId;
    }

    public void setNlpZdlId(Long nlpZdlId) {
        this.nlpZdlId = nlpZdlId;
    }

    public Long getNlpRfId() {
        return nlpRfId;
    }

    public void setNlpRfId(Long nlpRfId) {
        this.nlpRfId = nlpRfId;
    }

    public String getLeaseName() {
        return leaseName;
    }

    public void setLeaseName(String leaseName) {
        this.leaseName = leaseName;
    }

    public String getLeaseSpec() {
        return leaseSpec;
    }

    public void setLeaseSpec(String leaseSpec) {
        this.leaseSpec = leaseSpec;
    }

    public Float getLeaseNum() {
        return leaseNum;
    }

    public void setLeaseNum(Float leaseNum) {
        this.leaseNum = leaseNum;
    }

    public String getOwnerNo() {
        return ownerNo;
    }

    public void setOwnerNo(String ownerNo) {
        this.ownerNo = ownerNo;
    }

    public String getLeaseUuid() {
        return leaseUuid;
    }

    public void setLeaseUuid(String leaseUuid) {
        this.leaseUuid = leaseUuid;
    }

    public String getLessor() {
        return lessor;
    }

    public void setLessor(String lessor) {
        this.lessor = lessor;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getBussType() {
        return bussType;
    }

    public void setBussType(String bussType) {
        this.bussType = bussType;
    }

    public String getHexCell() {
        return hexCell;
    }

    public void setHexCell(String hexCell) {
        this.hexCell = hexCell;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
