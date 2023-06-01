package com.bean;

import java.util.Date;
import java.util.List;

/**
 * @Author jiajun.xiong
 * @Date 2021/4/6 10:31
 * @Version 1.0
 */
public class CheckInvoice {
    private String checkInvoiceId;//ID主键
    private String remarkId;//REMARK_ID关联任务表

    private String invoiceCode;//发票代码
    private String invoiceNumber;//发票号码
    private String invoiceDate;//开票日期
    private String invoiceName;//发票名称
    private String checkCount; //查验次数
    private String check_code;//校验码
    private String machineCode;//机器编号
    private String buyer;//购买方
    private String buyerNumber;//纳税人识别号(买方)
    private String buyerAddressTelephone;//地址、电话(买方)
    private String buyerBankAccount;//开户行及账号(买方)
    private String passwordArea;//密码区
    private String totalAmount;//不含税金额
    private String totalTax;//合计税额
    private String taxRate;//税率
    private String priceTaxChina;//价税合计(中文)
    private String priceTaxNumber;//价税合计(数字)
    private String seller;//销售方
    private String sellerNumber;//纳税人识别号(卖方)
    private String sellerAddressTelephone;//地址、电话(卖方)
    private String sellerBankAccount;//开户行及账号(卖方)

    private Date createDate;//创建时间
    private String checkScrap;//作废标志 N:正常 Y：已作废 3：红冲
    private Date lastCheckDate;//最近一次查验时间

    private String invoiceType;// 发票类型
    private String qrCode;// 二维码

    //机动车一手销售发票字段独有字段
    private String idNo;//购买方身份证号码/组织机构代码
    private String vehicleType;//车辆类型
    private String bandModel;// 厂牌型号
    private String produceArea;// 产地
    private String qualifiedNo;// 合格证号
    private String commodityInspectionNo;//进口证明书号
    private String certificateOfImport;//商检单号
    private String engineNo;//发动机号
    private String vehicleIdentificationNo;//车辆识别代码/车架号码
    private String salerPhone;//销售方电话
    private String salerBankAccount;//销售方账号
    private String salerAddress;//销售方地址
    private String salerBankName;//开户银行
    private String taxAuthorityName;// 主管税务机关
    private String taxAuthorityCode;//主管税务机关代码
    private String vehicleTonnage;//吨位
    private String limitedPeopleCount;//限乘人数



    //机动车二手销售发票
    private String buyerUnitOrIndividual;//买方单位/个人
    private String buyerUnitCodeOrIdNo;//单位代码/身份证号码
    private String buyerUnitOrIndividualAddr;//买方单位/个人地址
    private String buyerPhone;//买方电话号码
    private String sellerUnitOrIndividual;//卖方单位/个人
    private String sellerUnitCodeOrIdNo;//卖方单位/身份证号码
    private String sellerUnitOrIndividualAddr;//卖方单位/个人地址
    private String sellerPhone;//卖方电话号码
    private String licensePlate;//车牌证号
    private String registrationNo;//登记证号
    private String transferredVehicleOffice;//转入地车辆管理所名称
    private String carPrice;//车价合计小写
    private String lemonMarket;//二手车市场
    private String lemonMarketTaxNo;//二手车市场纳税人识别号
    private String lemonMarketBankAndAccount;//二手车市场开户银行和账号
    private String lemonMarketPhone;//二手车市场电话

    private String account;// 账号
    private String usedCarMarketAddress;// 二手车市场地址
    private String remark;// 备注
    private String catNumber;
    private String catCode;
    private String provinc;//省
    private String city;


    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsedCarMarketAddress() {
        return usedCarMarketAddress;
    }

    public void setUsedCarMarketAddress(String usedCarMarketAddress) {
        this.usedCarMarketAddress = usedCarMarketAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getCheckInvoiceId() {
        return checkInvoiceId;
    }

    public void setCheckInvoiceId(String checkInvoiceId) {
        this.checkInvoiceId = checkInvoiceId;
    }

    public String getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(String remarkId) {
        this.remarkId = remarkId;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public String getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(String checkCount) {
        this.checkCount = checkCount;
    }

    public String getCheck_code() {
        return check_code;
    }

    public void setCheck_code(String check_code) {
        this.check_code = check_code;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyerNumber() {
        return buyerNumber;
    }

    public void setBuyerNumber(String buyerNumber) {
        this.buyerNumber = buyerNumber;
    }

    public String getBuyerAddressTelephone() {
        return buyerAddressTelephone;
    }

    public void setBuyerAddressTelephone(String buyerAddressTelephone) {
        this.buyerAddressTelephone = buyerAddressTelephone;
    }

    public String getBuyerBankAccount() {
        return buyerBankAccount;
    }

    public void setBuyerBankAccount(String buyerBankAccount) {
        this.buyerBankAccount = buyerBankAccount;
    }

    public String getPasswordArea() {
        return passwordArea;
    }

    public void setPasswordArea(String passwordArea) {
        this.passwordArea = passwordArea;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getPriceTaxChina() {
        return priceTaxChina;
    }

    public void setPriceTaxChina(String priceTaxChina) {
        this.priceTaxChina = priceTaxChina;
    }

    public String getPriceTaxNumber() {
        return priceTaxNumber;
    }

    public void setPriceTaxNumber(String priceTaxNumber) {
        this.priceTaxNumber = priceTaxNumber;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSellerNumber() {
        return sellerNumber;
    }

    public void setSellerNumber(String sellerNumber) {
        this.sellerNumber = sellerNumber;
    }

    public String getSellerAddressTelephone() {
        return sellerAddressTelephone;
    }

    public void setSellerAddressTelephone(String sellerAddressTelephone) {
        this.sellerAddressTelephone = sellerAddressTelephone;
    }

    public String getSellerBankAccount() {
        return sellerBankAccount;
    }

    public void setSellerBankAccount(String sellerBankAccount) {
        this.sellerBankAccount = sellerBankAccount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCheckScrap() {
        return checkScrap;
    }

    public void setCheckScrap(String checkScrap) {
        this.checkScrap = checkScrap;
    }

    public Date getLastCheckDate() {
        return lastCheckDate;
    }

    public void setLastCheckDate(Date lastCheckDate) {
        this.lastCheckDate = lastCheckDate;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBandModel() {
        return bandModel;
    }

    public void setBandModel(String bandModel) {
        this.bandModel = bandModel;
    }

    public String getProduceArea() {
        return produceArea;
    }

    public void setProduceArea(String produceArea) {
        this.produceArea = produceArea;
    }

    public String getQualifiedNo() {
        return qualifiedNo;
    }

    public void setQualifiedNo(String qualifiedNo) {
        this.qualifiedNo = qualifiedNo;
    }

    public String getCommodityInspectionNo() {
        return commodityInspectionNo;
    }

    public void setCommodityInspectionNo(String commodityInspectionNo) {
        this.commodityInspectionNo = commodityInspectionNo;
    }

    public String getCertificateOfImport() {
        return certificateOfImport;
    }

    public void setCertificateOfImport(String certificateOfImport) {
        this.certificateOfImport = certificateOfImport;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getVehicleIdentificationNo() {
        return vehicleIdentificationNo;
    }

    public void setVehicleIdentificationNo(String vehicleIdentificationNo) {
        this.vehicleIdentificationNo = vehicleIdentificationNo;
    }

    public String getSalerPhone() {
        return salerPhone;
    }

    public void setSalerPhone(String salerPhone) {
        this.salerPhone = salerPhone;
    }

    public String getSalerBankAccount() {
        return salerBankAccount;
    }

    public void setSalerBankAccount(String salerBankAccount) {
        this.salerBankAccount = salerBankAccount;
    }

    public String getSalerAddress() {
        return salerAddress;
    }

    public void setSalerAddress(String salerAddress) {
        this.salerAddress = salerAddress;
    }

    public String getSalerBankName() {
        return salerBankName;
    }

    public void setSalerBankName(String salerBankName) {
        this.salerBankName = salerBankName;
    }

    public String getTaxAuthorityName() {
        return taxAuthorityName;
    }

    public void setTaxAuthorityName(String taxAuthorityName) {
        this.taxAuthorityName = taxAuthorityName;
    }

    public String getTaxAuthorityCode() {
        return taxAuthorityCode;
    }

    public void setTaxAuthorityCode(String taxAuthorityCode) {
        this.taxAuthorityCode = taxAuthorityCode;
    }

    public String getVehicleTonnage() {
        return vehicleTonnage;
    }

    public void setVehicleTonnage(String vehicleTonnage) {
        this.vehicleTonnage = vehicleTonnage;
    }

    public String getLimitedPeopleCount() {
        return limitedPeopleCount;
    }

    public void setLimitedPeopleCount(String limitedPeopleCount) {
        this.limitedPeopleCount = limitedPeopleCount;
    }

    public String getBuyerUnitOrIndividual() {
        return buyerUnitOrIndividual;
    }

    public void setBuyerUnitOrIndividual(String buyerUnitOrIndividual) {
        this.buyerUnitOrIndividual = buyerUnitOrIndividual;
    }

    public String getBuyerUnitCodeOrIdNo() {
        return buyerUnitCodeOrIdNo;
    }

    public void setBuyerUnitCodeOrIdNo(String buyerUnitCodeOrIdNo) {
        this.buyerUnitCodeOrIdNo = buyerUnitCodeOrIdNo;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getSellerUnitOrIndividual() {
        return sellerUnitOrIndividual;
    }

    public void setSellerUnitOrIndividual(String sellerUnitOrIndividual) {
        this.sellerUnitOrIndividual = sellerUnitOrIndividual;
    }

    public String getSellerUnitCodeOrIdNo() {
        return sellerUnitCodeOrIdNo;
    }

    public void setSellerUnitCodeOrIdNo(String sellerUnitCodeOrIdNo) {
        this.sellerUnitCodeOrIdNo = sellerUnitCodeOrIdNo;
    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getTransferredVehicleOffice() {
        return transferredVehicleOffice;
    }

    public void setTransferredVehicleOffice(String transferredVehicleOffice) {
        this.transferredVehicleOffice = transferredVehicleOffice;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getLemonMarket() {
        return lemonMarket;
    }

    public void setLemonMarket(String lemonMarket) {
        this.lemonMarket = lemonMarket;
    }

    public String getLemonMarketTaxNo() {
        return lemonMarketTaxNo;
    }

    public void setLemonMarketTaxNo(String lemonMarketTaxNo) {
        this.lemonMarketTaxNo = lemonMarketTaxNo;
    }

    public String getLemonMarketBankAndAccount() {
        return lemonMarketBankAndAccount;
    }

    public void setLemonMarketBankAndAccount(String lemonMarketBankAndAccount) {
        this.lemonMarketBankAndAccount = lemonMarketBankAndAccount;
    }

    public String getLemonMarketPhone() {
        return lemonMarketPhone;
    }

    public void setLemonMarketPhone(String lemonMarketPhone) {
        this.lemonMarketPhone = lemonMarketPhone;
    }


    public String getBuyerUnitOrIndividualAddr() {
        return buyerUnitOrIndividualAddr;
    }

    public void setBuyerUnitOrIndividualAddr(String buyerUnitOrIndividualAddr) {
        this.buyerUnitOrIndividualAddr = buyerUnitOrIndividualAddr;
    }

    public String getSellerUnitOrIndividualAddr() {
        return sellerUnitOrIndividualAddr;
    }

    public void setSellerUnitOrIndividualAddr(String sellerUnitOrIndividualAddr) {
        this.sellerUnitOrIndividualAddr = sellerUnitOrIndividualAddr;
    }

    public String getCatNumber() {
        return catNumber;
    }

    public void setCatNumber(String catNumber) {
        this.catNumber = catNumber;
    }

    public String getCatCode() {
        return catCode;
    }

    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getProvinc() {
        return provinc;
    }

    public void setProvinc(String provinc) {
        this.provinc = provinc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
