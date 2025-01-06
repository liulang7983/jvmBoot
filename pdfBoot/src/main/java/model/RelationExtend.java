package model;


import json.LeaseRepeatTypeEnum;
import json.RuleConditionEnum;
import json.RuleTypeEnum;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 租赁物重复扩展信息
 */

public class RelationExtend {
    /*
     中登主键id（nlp租赁物）
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long nlpZdlId;
    /*
      中登证明id
    */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long nlpRegId;

    /*
    中登文件id
    */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long nlpRfId;

    //名称或关键词
    private String name;

    //租赁物型号
    private String leaseSpec;

    //权属编号或发票号码
    private String ownerNo;

    //唯一识别码
    private String leaseUuid;

    //数量
    private Float leaseNum;

    //供应商
    private String supplier;
    //地址
    private String address;

    //出租人
    private String lessor;

    //登记证明编号
    private String registerNo;

    //业务类型
    private String bussType;

    //重复度
    private BigDecimal percent;

    //重复状态：0-不重复，1-重复
    private Integer status;

    //重复原因
    private String reason;

    //图像定位
    private String rt;

    private int pageNo;

    private String pdfName;

    private String dupContent;

    private int riskLevel;

    private String code;


    //nlp租赁物匹配相关构造
    public RelationExtend(model.Relation relation, ZdLeaseInfoDTO zdLease) {
        this.nlpZdlId = relation.getId();
        this.nlpRegId = relation.getNlpRegId();
        this.reason = relation.getMsg();
        this.percent = relation.getPercent();
        this.status = relation.getStatus();
        this.dupContent = relation.getText();
        this.rt = relation.getRt();
        this.riskLevel = relation.getRiskLevel();
        if (Objects.nonNull(zdLease)) {
            this.name = zdLease.getLeaseName();
            this.leaseSpec = zdLease.getLeaseSpec();
            this.ownerNo = zdLease.getOwnerNo();
            this.leaseUuid = zdLease.getLeaseUuid();
            this.lessor = zdLease.getLessor();
            this.leaseNum = zdLease.getLeaseNum();
            this.registerNo = zdLease.getRegisterNo();
            this.nlpRfId = zdLease.getNlpRfId();
            this.supplier = zdLease.getSupplier();
            this.address=zdLease.getAddress();
            this.bussType= "1";
            /*String hexCell = zdLease.getHexCell();
            if (CharSequenceUtil.isNotBlank(hexCell)){
                HexCell hc = JSON.parseObject(hexCell, HexCell.class);
                this.rt=hc.getItemcoord().getX()+","+hc.getItemcoord().getY()+","+hc.getItemcoord().getWidth()+","+hc.getItemcoord().getHeight();
            }*/
        }
        checkReason();
    }

    //登记报告匹配相关构造
    public RelationExtend(Relation relation, String registerNo, String bussType, String lessor, String leaseUuid){
        this.nlpRegId=relation.getNlpRegId();
        this.nlpRfId=relation.getNlpRfId();
        this.reason=relation.getMsg();
        this.percent=relation.getPercent();
        this.status=relation.getStatus();
        this.registerNo=registerNo;
        this.leaseUuid=leaseUuid;
        this.rt=relation.getRt();
        this.bussType= "1";
        this.lessor = lessor;
        this.dupContent = relation.getText();
        checkReason();
    }

    //关键字匹配相关构造
    public RelationExtend(String keyword, int riskLevel, String code, int nlpRegId, int nlpRfId, String rt, String registerNo, String bussType, String lessor) {
        this.nlpRfId = Long.valueOf(nlpRfId);
        this.nlpRegId = Long.valueOf(nlpRegId);
        this.reason = RuleTypeEnum.KEYSEARCH1.getName().substring(0, RuleTypeEnum.KEYSEARCH1.getName().length() - 1) + RuleConditionEnum.CONTAIN.getName();
        this.rt = rt;
        this.percent = BigDecimal.ONE;
        this.status = 1;
        this.registerNo = registerNo;
        this.name = keyword;
        this.bussType = "1";
        this.lessor = lessor;
        this.dupContent = keyword;
        this.riskLevel = riskLevel;
        this.code = code;
    }


    //由于重复原因改版，在此进行替换赋值操作
    private void checkReason(){
        if ("唯一识别码重复".equals(reason)&&Objects.nonNull(nlpZdlId)){
            reason=LeaseRepeatTypeEnum.B.text();
        }

        if (reason.startsWith("租赁财产唯一识别码重复")&&status==1){
            reason= LeaseRepeatTypeEnum.E.text();
        }

        for (LeaseRepeatTypeEnum repeatType : LeaseRepeatTypeEnum.values()) {
            if (reason.equals(repeatType.msg())){
                reason=repeatType.text();
            }
        }
    }

    public Long getNlpZdlId() {
        return nlpZdlId;
    }

    public void setNlpZdlId(Long nlpZdlId) {
        this.nlpZdlId = nlpZdlId;
    }

    public Long getNlpRegId() {
        return nlpRegId;
    }

    public void setNlpRegId(Long nlpRegId) {
        this.nlpRegId = nlpRegId;
    }

    public Long getNlpRfId() {
        return nlpRfId;
    }

    public void setNlpRfId(Long nlpRfId) {
        this.nlpRfId = nlpRfId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeaseSpec() {
        return leaseSpec;
    }

    public void setLeaseSpec(String leaseSpec) {
        this.leaseSpec = leaseSpec;
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

    public Float getLeaseNum() {
        return leaseNum;
    }

    public void setLeaseNum(Float leaseNum) {
        this.leaseNum = leaseNum;
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

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public String getDupContent() {
        return dupContent;
    }

    public void setDupContent(String dupContent) {
        this.dupContent = dupContent;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
