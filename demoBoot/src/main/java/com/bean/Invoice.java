package com.bean;

import java.util.Date;
import java.util.List;

/**
 * @Author jiajun.xiong
 * @Date 2021/4/6 10:31
 * @Version 1.0
 */
public class Invoice {
    private String invoiceId;//ID主键
    private String remarkId;//REMARK_ID关联任务表
    private String invoiceCode;//发票代码
    private String invoiceNumber;//发票号码
    private String invoiceDate;//开票日期
    private String invoiceName;//发票名称
    private String checkCode;//校验码
    private String machineCode;//机器编号
    private String buyer;//购买方
    private String buyerNumber;//纳税人识别号(买方)
    private String buyerAddressTelephone;//地址、电话(买方)
    private String buyerBankAccount;//开户行及账号(买方)
    private String passwordArea;//密码区
    private String totalAmount;//合计金额
    private String totalTax;//合计税额
    private String priceTaxChina;//价税合计(中文)
    private String priceTaxNumber;//价税合计(数字)
    private String seller;//销售方
    private String sellerNumber;//纳税人识别号(卖方)
    private String sellerAddressTelephone;//地址、电话(卖方)
    private String sellerBankAccount;//开户行及账号(卖方)
    private String remarks;//备注
    private String payee;//收款人
    private String toReview;//复核
    private String drawer;//开票人
    private String existSeal;//是否存在公章
    private String invoiceProvince;//省
    private String invoiceCity;//市
    private String isInvoiceUnite;//是否为发票联

    private List<InvoiceGoods> invoiceGoodsList;//发票货物集合

    private String printInvoiceCode;// 打印发票代码
    private String printInvoiceNumber;// 打印发票号码
    private String coupletSecondName;// 联次名称
    private String serviceType;// 服务类型
    private String tollMark;// 通行费标志
    private String issueFlag;// 是否代开
    private String purchaseFlag;// 是否收购
    private String invoiceConsumeType;// 发票消费类型
    private String carBoatTax;// 车船税
    private String productOilLogo;// 成品油标志
    private String checkCodeBft;// 校验码备选
    private String checkCodeAfterSixBft;// 校验码后六位备选
    private String invoiceNumberBft;// 发票号码备选
    private String licensePlateNumber;// 车牌号
    private String types;// 类型
    private String passDateBegin;// 通行日期起
    private String passDateEnd;// 通行日期止
    private String invoiceType;// 发票类型
    private String invoiceSealOfUnitName;//发票章单位名称
    private String invoiceSealOfCreditCode;//发票章信用代码
    private Date syncDate;//同步时间

    public String getPrintInvoiceCode() {
        return printInvoiceCode;
    }

    public void setPrintInvoiceCode(String printInvoiceCode) {
        this.printInvoiceCode = printInvoiceCode;
    }

    public String getPrintInvoiceNumber() {
        return printInvoiceNumber;
    }

    public void setPrintInvoiceNumber(String printInvoiceNumber) {
        this.printInvoiceNumber = printInvoiceNumber;
    }

    public String getCoupletSecondName() {
        return coupletSecondName;
    }

    public void setCoupletSecondName(String coupletSecondName) {
        this.coupletSecondName = coupletSecondName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getTollMark() {
        return tollMark;
    }

    public void setTollMark(String tollMark) {
        this.tollMark = tollMark;
    }

    public String getIssueFlag() {
        return issueFlag;
    }

    public void setIssueFlag(String issueFlag) {
        this.issueFlag = issueFlag;
    }

    public String getPurchaseFlag() {
        return purchaseFlag;
    }

    public void setPurchaseFlag(String purchaseFlag) {
        this.purchaseFlag = purchaseFlag;
    }

    public String getInvoiceConsumeType() {
        return invoiceConsumeType;
    }

    public void setInvoiceConsumeType(String invoiceConsumeType) {
        this.invoiceConsumeType = invoiceConsumeType;
    }

    public String getCarBoatTax() {
        return carBoatTax;
    }

    public void setCarBoatTax(String carBoatTax) {
        this.carBoatTax = carBoatTax;
    }

    public String getProductOilLogo() {
        return productOilLogo;
    }

    public void setProductOilLogo(String productOilLogo) {
        this.productOilLogo = productOilLogo;
    }

    public String getCheckCodeBft() {
        return checkCodeBft;
    }

    public void setCheckCodeBft(String checkCodeBft) {
        this.checkCodeBft = checkCodeBft;
    }

    public String getCheckCodeAfterSixBft() {
        return checkCodeAfterSixBft;
    }

    public void setCheckCodeAfterSixBft(String checkCodeAfterSixBft) {
        this.checkCodeAfterSixBft = checkCodeAfterSixBft;
    }

    public String getInvoiceNumberBft() {
        return invoiceNumberBft;
    }

    public void setInvoiceNumberBft(String invoiceNumberBft) {
        this.invoiceNumberBft = invoiceNumberBft;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getPassDateBegin() {
        return passDateBegin;
    }

    public void setPassDateBegin(String passDateBegin) {
        this.passDateBegin = passDateBegin;
    }

    public String getPassDateEnd() {
        return passDateEnd;
    }

    public void setPassDateEnd(String passDateEnd) {
        this.passDateEnd = passDateEnd;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
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

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getToReview() {
        return toReview;
    }

    public void setToReview(String toReview) {
        this.toReview = toReview;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

    public String getExistSeal() {
        return existSeal;
    }

    public void setExistSeal(String existSeal) {
        this.existSeal = existSeal;
    }

    public String getInvoiceProvince() {
        return invoiceProvince;
    }

    public void setInvoiceProvince(String invoiceProvince) {
        this.invoiceProvince = invoiceProvince;
    }

    public String getInvoiceCity() {
        return invoiceCity;
    }

    public void setInvoiceCity(String invoiceCity) {
        this.invoiceCity = invoiceCity;
    }

    public String getIsInvoiceUnite() {
        return isInvoiceUnite;
    }

    public void setIsInvoiceUnite(String isInvoiceUnite) {
        this.isInvoiceUnite = isInvoiceUnite;
    }

    public String getInvoiceSealOfUnitName() {
        return invoiceSealOfUnitName;
    }

    public void setInvoiceSealOfUnitName(String invoiceSealOfUnitName) {
        this.invoiceSealOfUnitName = invoiceSealOfUnitName;
    }

    public String getInvoiceSealOfCreditCode() {
        return invoiceSealOfCreditCode;
    }

    public void setInvoiceSealOfCreditCode(String invoiceSealOfCreditCode) {
        this.invoiceSealOfCreditCode = invoiceSealOfCreditCode;
    }

    public List<InvoiceGoods> getInvoiceGoodsList() {
        return invoiceGoodsList;
    }

    public void setInvoiceGoodsList(List<InvoiceGoods> invoiceGoodsList) {
        this.invoiceGoodsList = invoiceGoodsList;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId='" + invoiceId + '\'' +
                ", remarkId='" + remarkId + '\'' +
                ", invoiceCode='" + invoiceCode + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", invoiceName='" + invoiceName + '\'' +
                ", checkCode='" + checkCode + '\'' +
                ", machineCode='" + machineCode + '\'' +
                ", buyer='" + buyer + '\'' +
                ", buyerNumber='" + buyerNumber + '\'' +
                ", buyerAddressTelephone='" + buyerAddressTelephone + '\'' +
                ", buyerBankAccount='" + buyerBankAccount + '\'' +
                ", passwordArea='" + passwordArea + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", totalTax='" + totalTax + '\'' +
                ", priceTaxChina='" + priceTaxChina + '\'' +
                ", priceTaxNumber='" + priceTaxNumber + '\'' +
                ", seller='" + seller + '\'' +
                ", sellerNumber='" + sellerNumber + '\'' +
                ", sellerAddressTelephone='" + sellerAddressTelephone + '\'' +
                ", sellerBankAccount='" + sellerBankAccount + '\'' +
                ", remarks='" + remarks + '\'' +
                ", payee='" + payee + '\'' +
                ", toReview='" + toReview + '\'' +
                ", drawer='" + drawer + '\'' +
                ", existSeal='" + existSeal + '\'' +
                ", invoiceProvince='" + invoiceProvince + '\'' +
                ", invoiceCity='" + invoiceCity + '\'' +
                ", isInvoiceUnite='" + isInvoiceUnite + '\'' +
                ", invoiceGoodsList=" + invoiceGoodsList +
                ", printInvoiceCode='" + printInvoiceCode + '\'' +
                ", printInvoiceNumber='" + printInvoiceNumber + '\'' +
                ", coupletSecondName='" + coupletSecondName + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", tollMark='" + tollMark + '\'' +
                ", issueFlag='" + issueFlag + '\'' +
                ", purchaseFlag='" + purchaseFlag + '\'' +
                ", invoiceConsumeType='" + invoiceConsumeType + '\'' +
                ", carBoatTax='" + carBoatTax + '\'' +
                ", productOilLogo='" + productOilLogo + '\'' +
                ", checkCodeBft='" + checkCodeBft + '\'' +
                ", checkCodeAfterSixBft='" + checkCodeAfterSixBft + '\'' +
                ", invoiceNumberBft='" + invoiceNumberBft + '\'' +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                ", types='" + types + '\'' +
                ", passDateBegin='" + passDateBegin + '\'' +
                ", passDateEnd='" + passDateEnd + '\'' +
                ", invoiceType='" + invoiceType + '\'' +
                ", invoiceSealOfUnitName='" + invoiceSealOfUnitName + '\'' +
                ", invoiceSealOfCreditCode='" + invoiceSealOfCreditCode + '\'' +
                '}';
    }
}
