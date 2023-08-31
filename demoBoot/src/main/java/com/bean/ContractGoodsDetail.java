package com.bean;

import lombok.Data;

/**
 * @author ming.li
 * @date 2023/8/18 14:13
 */
@Data
public class ContractGoodsDetail {
    private Long id;
    private Long contractId;
    private String orderId;//提单号
    private String goodsName;//产品名称
    private String mark;//牌号
    private String texture;//材质
    private String spec;//规格型号
    private String manufacturer;//厂家
    private String num;//数量
    private String weigth;//重量
    private String unitPrice;//单价
    private String totalPrice;//总金额
    private String json;
}
