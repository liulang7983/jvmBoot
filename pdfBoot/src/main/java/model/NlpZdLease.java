package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hex-xjiang
 * @version 1.0
 * @description: 附件NLP提取结果表实体类
 * @date 2022/1/14 16:22
 */

public class NlpZdLease {

    /*
     主键ID
     */
    private Long nlpZdlId;
    /*
     NLP查重任务表主键ID
     */
    private Long nlpCtId;
    /*
     NLP查重登记信息附件表主键ID
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
     金额
     */
    private BigDecimal leasePrice;
    /*
     行号
     */
    private Long rowNo;
    /*
     租赁物对应表格坐标信息
     */
    private String hexCell;
    /*
     租户ID
     */
    private String tenantId;
    /*
     组织ID
     */
    private String orgId;
    /*
     创建时间
     */
    private Date crtTs;
    /*
     创建者
     */
    private String crtUser;
    /*
     创建时间
     */
    private Date udtTs;
    /*
     更新人员
     */
    private String udtUser;

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

    public Long getNlpCtId() {
        return nlpCtId;
    }

    public void setNlpCtId(Long nlpCtId) {
        this.nlpCtId = nlpCtId;
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

    public BigDecimal getLeasePrice() {
        return leasePrice;
    }

    public void setLeasePrice(BigDecimal leasePrice) {
        this.leasePrice = leasePrice;
    }

    public Long getRowNo() {
        return rowNo;
    }

    public void setRowNo(Long rowNo) {
        this.rowNo = rowNo;
    }

    public String getHexCell() {
        return hexCell;
    }

    public void setHexCell(String hexCell) {
        this.hexCell = hexCell;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Date getCrtTs() {
        return crtTs;
    }

    public void setCrtTs(Date crtTs) {
        this.crtTs = crtTs;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    public Date getUdtTs() {
        return udtTs;
    }

    public void setUdtTs(Date udtTs) {
        this.udtTs = udtTs;
    }

    public String getUdtUser() {
        return udtUser;
    }

    public void setUdtUser(String udtUser) {
        this.udtUser = udtUser;
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
