package model;

/**
 * 租赁物跑批任务清单项DTO
 */

public class RunBatchTaskItemDTO {
    /*
     业务流水号
     */

    private String extId;

    //////应收账款信息

    /**
     * 债务人
     */
    private String debtor;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 发票号码
     */
    private String invoiceNo;

    //////租赁物信息

    /**
     * 租赁物名称
     */
    private String leaseName;

    /**
     * 租赁物型号
     */
    private String leaseSpec;

    /**
     * 权属编号
     */
    private String ownerNo;

    /**
     * 唯一识别码
     */
    private String leaseUuid;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 地址
     */
    private String address;

    /**
     * 规则模型编码
     */
    private String modelCode;

    //////其他信息

    /**
     * 业务关键词1
     */
    private String keySearch1;

    /**
     * 业务关键词2
     */
    private String keySearch2;

    /**
     * 业务关键词3
     */
    private String keySearch3;

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getDebtor() {
        return debtor;
    }

    public void setDebtor(String debtor) {
        this.debtor = debtor;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
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

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
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
}
