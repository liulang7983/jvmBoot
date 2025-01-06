package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ming.li
 * @date 2022/1/19 9:33
 */


public class NlpZdLeaseVO extends NlpZdLease {

    /*
     登记证明编号
     */
    private String registerNo;
    /*
     合同编号
     */
    private String contractNo;
    /*
     业务类型(A00100:应收账款质押 A00200 应收账款转让(保理) B00000:融资租赁 N00000:其他动产和权力担保业务  N01600:动产留置权
     P00100:生产设备 N01700:所有权登记 N10000:保证金质押 N10010:存货质押 N10020:动产信托 N10040:存款单、仓单、提单质押 000000:企查查)
     */
    private String bussType;
    /*
     相同合同号下所有登记编号+业务类型的拼接信息 contractNo+bussType
     */
    private String cNBT;
    /*
    登记时间
     */
    private Date registerTime;
    /*
    登记信息表记录ID
     */
    private Long nlpRegId;
    /*
     重复度
     */
    private BigDecimal dupPercent ;
    /*
    关联关系ID
     */
    private Long nlpRId;
    /*
    重复状态
     */
    private Integer dupStatus;
    /*
    查重结论说明
     */
    private String dupMsg;
    /*
    业务类型详情
     */
    private String bussTypeInformation;
    /*
    重复度百分比
     */
    private String dupPercentInformation;
    /*
    文件所属类型
     */
    private Integer nlpRfType;

    private Long nlpLId;

    private boolean stepFlag;

    public String getcNBT() {
        return cNBT;
    }

    public void setcNBT(String cNBT) {
        this.cNBT = cNBT;
    }
}
