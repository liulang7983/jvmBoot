package model;

import com.alibaba.fastjson.JSONArray;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 租赁物详情
 */

public class NlpLease {
    /*
     主键ID
     */
    private Long nlpLId;
    /*
     NLP查重任务表主键ID
     */
    private Long nlpCtId;
    /*
     租赁物信息明细表主键ID
     */
    private Long ldId;
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
    租赁物单位
     */
    private  String leaseUnit;
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
     重复状态：0-不重复，1-重复
     */
    private Integer dupStatus;
    /*
     合计重复数量
     */
    private Float allDupNum;
    /*
    修改状态 0，未改；1，用户修改；2，关键字重复修改
     */
    private Integer modifyStatus;
    /*
    修改状态操作员
     */
    private String modifyUser;
    /*
    修改原因
     */
    private String modifyReason;
    /*
    修改状态时间
     */
    private Date modifyTime;
    /*
     租户ID
     */
    private String tenantId;
    /*
     组织ID
     */
    private String orgId;
    /*
     中登比对结果字段
     */
    private String relation;
    /*
     最大重复度
     */
    private Integer maxDup;

    /*
    全文匹配坐标
    */
    private String hexJson;
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

    /*
     业务ID
     */
    private String extId;

    /* 业务字段 */

    private String keySearch1;

    private String keySearch2;

    private String keySearch3;
    //规则模型编码

    private String modelCode;
    //供应商

    private String supplier;
    //地址

    private String address;

    /*
    中登重复租赁物
     */
    private List<NlpZdLeaseVO> zdLeaseVoList;

    /*
    重复数量原因
     */
    private String leaseMsg;

    //重复记录列表
    private List<RelationExtend> relationExtendList;

    private List<String> relationReasonList;

    private JSONArray reasonAndNums;

    private int dupNum;

    private int riskLevel;

    private Set<String> dupValues;

    //序号
    private Integer sortNo;

    public NlpLease(RunBatchTaskItemDTO item) {
        this.extId=item.getExtId();
        this.leaseName=item.getLeaseName();
        this.leaseSpec=item.getLeaseSpec();
        this.ownerNo=item.getOwnerNo();
        this.leaseUuid=item.getLeaseUuid();
        this.supplier=item.getSupplier();
        this.address=item.getAddress();
        this.keySearch1=item.getKeySearch1();
        this.keySearch2=item.getKeySearch2();
        this.keySearch3=item.getKeySearch3();
        this.modelCode=item.getModelCode();
        this.dupStatus=0;
    }

    public String getRepeatReason() {
        return null;
    }

    public Long getNlpLId() {
        return nlpLId;
    }

    public void setNlpLId(Long nlpLId) {
        this.nlpLId = nlpLId;
    }

    public Long getNlpCtId() {
        return nlpCtId;
    }

    public void setNlpCtId(Long nlpCtId) {
        this.nlpCtId = nlpCtId;
    }

    public Long getLdId() {
        return ldId;
    }

    public void setLdId(Long ldId) {
        this.ldId = ldId;
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

    public String getLeaseUnit() {
        return leaseUnit;
    }

    public void setLeaseUnit(String leaseUnit) {
        this.leaseUnit = leaseUnit;
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

    public Integer getDupStatus() {
        return dupStatus;
    }

    public void setDupStatus(Integer dupStatus) {
        this.dupStatus = dupStatus;
    }

    public Float getAllDupNum() {
        return allDupNum;
    }

    public void setAllDupNum(Float allDupNum) {
        this.allDupNum = allDupNum;
    }

    public Integer getModifyStatus() {
        return modifyStatus;
    }

    public void setModifyStatus(Integer modifyStatus) {
        this.modifyStatus = modifyStatus;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public void setModifyReason(String modifyReason) {
        this.modifyReason = modifyReason;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Integer getMaxDup() {
        return maxDup;
    }

    public void setMaxDup(Integer maxDup) {
        this.maxDup = maxDup;
    }

    public String getHexJson() {
        return hexJson;
    }

    public void setHexJson(String hexJson) {
        this.hexJson = hexJson;
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

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getKeySearch1() {
        return keySearch1;
    }

    public void setKeySearch1(String keySearch1) {
        this.keySearch1 = keySearch1;
    }

    public String getKeySearch2() {
        return keySearch2;
    }

    public void setKeySearch2(String keySearch2) {
        this.keySearch2 = keySearch2;
    }

    public String getKeySearch3() {
        return keySearch3;
    }

    public void setKeySearch3(String keySearch3) {
        this.keySearch3 = keySearch3;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
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

    public List<NlpZdLeaseVO> getZdLeaseVoList() {
        return zdLeaseVoList;
    }

    public void setZdLeaseVoList(List<NlpZdLeaseVO> zdLeaseVoList) {
        this.zdLeaseVoList = zdLeaseVoList;
    }

    public String getLeaseMsg() {
        return leaseMsg;
    }

    public void setLeaseMsg(String leaseMsg) {
        this.leaseMsg = leaseMsg;
    }

    public List<RelationExtend> getRelationExtendList() {
        return relationExtendList;
    }

    public void setRelationExtendList(List<RelationExtend> relationExtendList) {
        this.relationExtendList = relationExtendList;
    }

    public List<String> getRelationReasonList() {
        return relationReasonList;
    }

    public void setRelationReasonList(List<String> relationReasonList) {
        this.relationReasonList = relationReasonList;
    }

    public JSONArray getReasonAndNums() {
        return reasonAndNums;
    }

    public void setReasonAndNums(JSONArray reasonAndNums) {
        this.reasonAndNums = reasonAndNums;
    }

    public int getDupNum() {
        return dupNum;
    }

    public void setDupNum(int dupNum) {
        this.dupNum = dupNum;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Set<String> getDupValues() {
        return dupValues;
    }

    public void setDupValues(Set<String> dupValues) {
        this.dupValues = dupValues;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}
